package ui

import reports.BalanceReportManager
import reports.MonthlySummaryManager
import utils.ResultStatus
import java.time.LocalDate

class ReportViewController(private val monthlySummaryManager : MonthlySummaryManager, private val balanceReportManager: BalanceReportManager) {

    fun getMonthlySummary(month:Int, year:Int){
        // call getMonthlySummary method from MonthlySummaryManager class
        val result = monthlySummaryManager.getMonthlySummary(year, month)
        UiUtils.displayMessage("monthly summary for this date ${month}/${year} is .......")
        if (result is ResultStatus.Success){
            UiUtils.displayMessage("All categories: ")

            result.data.categorySummaries.forEach {
                UiUtils.displayMessage("${it.category.name}: ${it.type} --> ${it.totalAmount}")
            }
            UiUtils.displayMessage("All Transactions: ")

            result.data.transactions.forEach {
                UiUtils.displayMessage("${it.description} : ${it.type} --> ${it.amount}")
            }
        }else if (result is ResultStatus.Error){
            UiUtils.displayMessage(result.errorMessage)
        }else if (result is ResultStatus.Empty){
            UiUtils.displayMessage(result.message)
        }

    }

    fun getBalanceReport(startDate : LocalDate){
        UiUtils.displayMessage("retrieving balance report for period ${startDate} to ${LocalDate.now()}")
        val result = balanceReportManager.getBalanceReport(startDate)
        when (result) {
            is ResultStatus.Success -> {
                UiUtils.displayMessage("----------------")

                UiUtils.displayMessage("Balance Report:")
                UiUtils.displayMessage("----------------")

                UiUtils.displayMessage("Total Income :${result.data.totalIncome}")
                UiUtils.displayMessage("Total Expenses :${result.data.totalExpenses}")
                UiUtils.displayMessage("----------------")
                UiUtils.displayMessage("Net Balance :${result.data.netBalance}")


            }
            is ResultStatus.Error -> {
                UiUtils.displayMessage(result.errorMessage)
            }
            is ResultStatus.Empty -> UiUtils.displayMessage(result.message)
        }

    }
}