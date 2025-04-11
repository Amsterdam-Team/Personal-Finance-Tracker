package reports

import models.*
import models.reports.CategorySummary
import models.reports.MonthlySummary
import saver.IFileManager
import utils.ResultStatus
import java.time.LocalDate

class MonthlySummaryManager (private val fileManger: IFileManager){

    fun getMonthlySummary(
        year: Int,
        month: Int
    ): ResultStatus<MonthlySummary> {
        validateDateAndMonth(year, month)?.let { return ResultStatus.Error(it) }

        val filteredTransactions = getAllTransactions().filter {
            it.date.year == year && it.date.monthValue == month
        }

        if (filteredTransactions.isEmpty()) {
            return ResultStatus.Empty("There are no transactions this month")
        }

        val categorySummaries = filteredTransactions
            .groupBy { it.category to it.type }
            .map { (categoryType, transactions) ->
                CategorySummary(
                    category = categoryType.first,
                    type = categoryType.second,
                    totalAmount = transactions.sumOf { it.amount }
                )
            }
            .sortedByDescending { it.totalAmount }

        return ResultStatus.Success(
            MonthlySummary(
                categorySummaries = categorySummaries,
                transactions = filteredTransactions
            )
        )
    }



    private fun validateDateAndMonth(year: Int, month: Int): String? {
        val currentDate = LocalDate.now()
        return when {
            month !in 1..12 -> "Month must be between 1 and 12"
            year < 2000 -> "Year must be 2000 or later"
            year > currentDate.year -> "Cannot view summary for future years"
            year == currentDate.year && month > currentDate.monthValue ->
                "Cannot view summary for future months"
            else -> null
        }
    }


private fun getAllTransactions(): List<Transaction>{
    return fileManger.getAllObjects(Transaction::class) as List<Transaction>
}


}