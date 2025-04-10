package models.reports

sealed class MonthlySummaryResult(){
    data class Error(val errorMessage: String) : MonthlySummaryResult()
    object NoTransactions : MonthlySummaryResult()
    data class Success(val monthlySummary: MonthlySummaryModel) : MonthlySummaryResult()
}
