package ui

import reports.BalanceReportManager
import reports.MonthlySummaryManager
import utils.ResultStatus
import java.time.LocalDate

class ReportViewController(private val monthlySummaryManager : MonthlySummaryManager, private val balanceReportManager: BalanceReportManager) {

    fun getMonthlySummary(month:Int, year:Int){
        // call getMonthlySummary method from MonthlySummaryManager class
        val result = monthlySummaryManager.getMonthlySummary(month, year)
        UiUtils.displayMessage("monthly summary for this date ${month}/${year} is .......")
        if (result is ResultStatus.Success){
            UiUtils.displayMessage(result.data.toString())
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
                UiUtils.displayMessage(result.data.toString())
            }
            is ResultStatus.Error -> {
                UiUtils.displayMessage(result.errorMessage)
            }
            is ResultStatus.Empty -> UiUtils.displayMessage(result.message)
        }

    }
}