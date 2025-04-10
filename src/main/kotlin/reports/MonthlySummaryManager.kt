package reports

import models.*
import models.reports.CategorySummary
import models.reports.MonthlySummary
import utils.ResultStatus
import java.time.LocalDate

class MonthlySummaryManager {

    fun getMonthlySummary(
        year: Int,
        month: Int,
        transactions: List<Transaction>
    ): ResultStatus<MonthlySummary> {
        validateDate(year,month)?.let {
            return ResultStatus.Error(it)
        }
        val filteredTransactions =  transactions
            .filter {
                it.date.year == year &&
                        it.date.monthValue == month
            }
        if(filteredTransactions.isEmpty()){
            return ResultStatus.Empty("There Is No Transaction In This Month")
        }
        val categorySummaries = mutableListOf<CategorySummary>()
        filteredTransactions.forEachIndexed { _, transaction ->
            var itemFound = false
            categorySummaries.forEachIndexed {  _,categorySummary ->
                if(transaction.category.id==categorySummary.category.id){
                    categorySummary.totalAmount+=transaction.amount
                    itemFound = true
                }
            }
            if(!itemFound){
                categorySummaries.add(
                    CategorySummary(transaction.category,
                        transaction.type,
                        transaction.amount)
                )
            }
        }

        return ResultStatus.Success(
            MonthlySummary(
                categorySummaries = categorySummaries.sortedByDescending { it.totalAmount },
                transactions = filteredTransactions
            )
        )
    }



    private fun validateDate(year: Int, month: Int):  String? {
        if (month < 1 || month > 12) {
            return "Month must be between 1 and 12"
        }
        val currentDate = LocalDate.now()
        if (year > currentDate.year) {
            return "Cannot view summary for future years"
        }
        if (year == currentDate.year && month > currentDate.monthValue) {
            return "Cannot view summary for future months"
        }
        return null
    }





}