package ui

class ReportViewController {

    fun getMonthlySummary(month:Int, year:Int){
        // call getMonthlySummary method from MonthlySummaryManager class
        UiUtils.displayMessage("monthly summary for this date ${month}/${year} is .......")
    }

    fun getBalanceReport(){
        UiUtils.displayMessage("creating your balance report now .....")
        UiUtils.displayMessage("your balance report is .")
    }
}