package data

import models.MonthlySummaryReport
import models.Response
import models.Transaction
import java.time.LocalDate

interface DataSource {
    fun addTransaction(transaction: Transaction): Response<String>
    fun viewTransaction(transactionName: String): Response<Transaction>
    fun deleteTransaction(transactionName: String): Response<String>
    fun editTransaction(transactionName: String, newTransaction: Transaction): Response<String>
    fun getMonthlySummaryReport(month: LocalDate ): Response<MonthlySummaryReport>
    fun getBalanceReport() : Response<String> // or may be other representative data of balance report

}

