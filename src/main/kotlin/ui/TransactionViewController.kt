package ui

import managers.TransactionManager
import models.Category
import models.Transaction
import models.TransactionType
import utils.ResultStatus
import java.time.LocalDate
import java.util.*

//val transactionManager: TransactionManager, val categoryManager: CategoryManager
class TransactionViewController (val transactionManager: TransactionManager){
    fun addTransaction() {
        var transaction = UiUtils.getTransactionFromUser()
        val result = transactionManager.addTransaction(transaction)
        when(result){
            is ResultStatus.Success -> {
                UiUtils.displayMessage(result.data)

            }
            is ResultStatus.Error -> {
                UiUtils.displayMessage(result.errorMessage)

            }
            is ResultStatus.Empty -> {
                UiUtils.displayMessage(result.message)
            }
            else -> Unit
        }
    }


    fun editTransaction(id: UUID){
        // get transaction with this specific id
        // get the new data but we need to present the old data as well
        // get trans based on id
        // lets pretend this is the required transaction
        val t = Transaction(
            id = UUID.randomUUID(),
            description = "bought apple pen",
            amount = 39000.0,
            category = Category(id = UUID.randomUUID(), "electronic"),
            type = TransactionType.EXPENSE,
            date = LocalDate.now()
        )
        // get old transaction based on the id
        // edit the old transaction with this new transaction

//        val newEditedTransaction = UiUtils.getTransactionEditFromUser(t)
        val res = transactionManager.viewTransactionById(id.toString())
        var newEditiedTransaction: Transaction
        when (res) {
            is ResultStatus.Success -> {
                newEditiedTransaction = UiUtils.getTransactionEditFromUser(res.data)
            }
            is ResultStatus.Error -> {
                UiUtils.displayMessage(res.errorMessage)
                return
            }
            is ResultStatus.Empty -> {
                UiUtils.displayMessage(res.message)
                return
            }

        }

        val result = transactionManager.editTransaction(newEditiedTransaction)
        when(result){
            is ResultStatus.Success -> {
                UiUtils.displayMessage(result.data)

            }
            is ResultStatus.Error-> {
                UiUtils.displayMessage(result.errorMessage)

            }
            else -> Unit
        }

    }
    fun deleteTransaction(id :UUID){
        val result = transactionManager.deleteTransaction(id)
        when(result){
            is ResultStatus.Success -> {
                UiUtils.displayMessage(result.data)
            }
            is ResultStatus.Error -> {
                UiUtils.displayMessage(result.errorMessage)
            }
            is ResultStatus.Empty -> {
                UiUtils.displayMessage(result.message)
            }
            else -> Unit
        }
    }
    fun getAllTransactions(){
        val trnasList = listOf<Transaction>(
            Transaction(
                id = UUID.randomUUID(),
                description = "bought apple pen",
                amount = 39000.0,
                category = Category(id = UUID.randomUUID(), "electronic"),
                type = TransactionType.EXPENSE,
                date = LocalDate.now()
            ),
            Transaction(
                id = UUID.randomUUID(),
                description = "bought apple pen",
                amount = 39000.0,
                category = Category(id = UUID.randomUUID(), "electronic"),
                type = TransactionType.EXPENSE,
                date = LocalDate.now()
            ),
            Transaction(
                id = UUID.randomUUID(),
                description = "bought apple pen",
                amount = 39000.0,
                category = Category(id = UUID.randomUUID(), "electronic"),
                type = TransactionType.EXPENSE,
                date = LocalDate.now()
            )

        )
        var result = transactionManager.viewAllTransactions()
        when(result){
            is ResultStatus.Success -> {
                for (trn in result.data){
                    UiUtils.displayMessage("${trn.id}: ${trn.description}")
                }
            }
            else -> Unit
        }


    }
    fun getTransactionById(id: UUID) {
        val result= transactionManager.viewTransactionById(id.toString())
        when(result){
            is ResultStatus.Success -> {
                UiUtils.displayMessage("The transaction: ${result.data.toString()}")
            }
            is ResultStatus.Error -> {
                UiUtils.displayMessage(result.errorMessage)
            }
            is ResultStatus.Empty -> {
                UiUtils.displayMessage(result.message)
            }
            else -> Unit
        }
    }

}