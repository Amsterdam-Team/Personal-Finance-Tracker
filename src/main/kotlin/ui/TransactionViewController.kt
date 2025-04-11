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
        println(transaction)
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
        var newEditiedTransaction = UiUtils.getTransactionFromUser()


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
        println("deleting this transaction")
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
        var result = transactionManager.getAllTransaction()
        when(result){
            is ResultStatus.Success -> {
                for (trn in result.data){
                    UiUtils.displayMessage("${trn.id}: ${trn.description}")
                }
            }
            else -> Unit
        }


    }

}