package data

import models.MonthlySummaryReport
import models.Response
import models.Transaction
import java.time.LocalDate

class MemoryDataSource(var data : List<Transaction>) : DataSource {
    override fun getMonthlySummaryReport(month: LocalDate): Response<MonthlySummaryReport> {
        TODO("Not yet implemented")
    }

    override fun getBalanceReport(): Response<String> {
        TODO("Not yet implemented")
    }

    override fun addTransaction(transaction: Transaction): Response<String> {
        TODO("Not yet implemented")
    }

    override fun viewTransaction(transactionName: String): Response<Transaction> {
        TODO("Not yet implemented")
    }

    override fun deleteTransaction(transactionName: String): Response<String> {
        TODO("Not yet implemented")
    }

    override fun editTransaction(transactionName: String, newTransaction: Transaction): Response<String> {
        TODO("Not yet implemented")
    }
}