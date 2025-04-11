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

        UiUtils.displayMessage("Transaction added")
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

        val newEditedTransaction = UiUtils.getTransactionEditFromUser(t)
        val result = transactionManager.editTransaction(newEditedTransaction)
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
        return
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

        for (trn in trnasList){
            UiUtils.displayMessage("${trn.id}: ${trn.description}")
        }

    }

}