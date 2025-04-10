package reports

import models.BalanceReport
import models.Transaction
import models.TransactionType
import saver.IFileManager
import utils.ResultStatus
import java.time.LocalDate

class BalanceReportManager(private val fileManager: IFileManager) {
    fun getBalanceReport(startDate: LocalDate, endDate: LocalDate = LocalDate.now()): ResultStatus<BalanceReport> {
        val transactions = fileManager.getAllObjects(Transaction::class.java)

        val transactionsInTheRange = filterTransactionsByDateRange(
            transactions = transactions, startDate = startDate, endDate = endDate
        )

        return when {
            endDate.isAfter(LocalDate.now()) -> ResultStatus.Error(ERROR_END_DATE_AFTER_TODAY)
            startDate.isAfter(endDate) -> ResultStatus.Error(ERROR_START_DATE_AFTER_END_DATE)
            transactionsInTheRange.isEmpty() -> ResultStatus.Empty(EMPTY_REPORT_BALANCE)
            else -> {
                val balance = calculateBalance(transactionsInTheRange)
                ResultStatus.Success(balance)
            }
        }
    }


    private fun filterTransactionsByDateRange(
        transactions: List<Transaction>,
        startDate: LocalDate,
        endDate: LocalDate
    ): List<Transaction> {
        return transactions.filter { transaction ->
            transaction.date.isAfter(startDate.minusDays(1)) &&
            transaction.date.isBefore(endDate.plusDays(1))
        }
    }

    private fun calculateBalance(transactionsInTheRange: List<Transaction>): BalanceReport {
        val totalIncome = transactionsInTheRange.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val totalExpenses = transactionsInTheRange.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }

        val netBalance = totalIncome - totalExpenses

        return BalanceReport(totalIncome, totalExpenses, netBalance)
    }


    companion object {
        private const val ERROR_END_DATE_AFTER_TODAY =
            "The end date cannot be after the current date. Please provide a valid end date."
        private const val ERROR_START_DATE_AFTER_END_DATE =
            "The start date cannot be after the end date. Please adjust your date range."
        private const val EMPTY_REPORT_BALANCE =
            "No transactions found in the selected date range. Please check the dates or add transactions."
    }

}

