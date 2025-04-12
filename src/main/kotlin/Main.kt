import managers.CategoryManager
import managers.TransactionManager
import reports.BalanceReportManager
import reports.MonthlySummaryManager
import saver.FileManagerImpl
import ui.ApplicationView
import ui.CategoryViewController
import ui.ReportViewController
import ui.TransactionViewController

fun main(args: Array<String>) {

    val fileManager = FileManagerImpl()
    val transactionViewController = TransactionViewController(TransactionManager(fileManager))
    val reportViewController =
        ReportViewController(MonthlySummaryManager(fileManager), BalanceReportManager(fileManager))
    val categoryViewController = CategoryViewController(CategoryManager(fileManager))
    val applicationView =
        ApplicationView(transactionViewController, reportViewController, categoryViewController)
    applicationView.start()
}