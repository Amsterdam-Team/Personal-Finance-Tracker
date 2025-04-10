package reports

import models.*
import java.time.LocalDate

class MonthlySummary {


    private fun checkDate(year: Int, month: Int):  String {
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
        return SUCCESS
    }
    fun getMonthlySummary(
        year: Int,
        month: Int,
        transactions: List<Transaction>
    ): MonthlySummaryResult{
        val dateResult = checkDate(year,month)
        if(dateResult != SUCCESS){
            return MonthlySummaryResult.Error(dateResult)
        }
        val filteredTransactions =  transactions
            .filter {
                it.date.year == year &&
                        it.date.monthValue == month
            }
        if(filteredTransactions.isEmpty()){
            return MonthlySummaryResult.NoTransactions
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
                if(itemFound){
                    categorySummaries.add(
                        CategorySummary(transaction.category,
                            transaction.type,
                            transaction.amount)
                    )
                }
            }
            return MonthlySummaryResult.Success(
                MonthlySummaryModel(
                    categorySummaries = categorySummaries.sortedByDescending { it.totalAmount },
                    transactions = filteredTransactions
                )
            )
    }





    companion object{
        private const val SUCCESS = "Success"
    }
}