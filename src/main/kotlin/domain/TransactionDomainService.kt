package domain

import data.DataSource
import models.Response
import models.Transaction

class TransactionDomainService(val dataSource: DataSource) {
    fun addTransaction(transaction: Transaction): Response<String> {
        //TODO
        throw Exception()

    }
    fun viewTransaction(transactionName: String): Response<Transaction> {
        //TODO
        throw Exception()

    }
    fun deleteTransaction(transactionName: String): Response<String> {
        //TODO
        throw Exception()

    }
    fun editTransaction(transactionName: String, newTransaction: Transaction): Response<String> {
        //TODO
        throw Exception()

    }
}