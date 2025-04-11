package ui

import models.Category
import models.Transaction
import models.TransactionType
import java.time.LocalDate
import java.util.*

//val transactionManager: TransactionManager, val categoryManager: CategoryManager
class TransactionViewController (){
    fun addTransaction() {
        var transaction = UiUtils.getTransactionFromUser()

        UiUtils.displayMessage("Transaction added")
        println(transaction)
    }

    fun addCategory() {
        var category = UiUtils.getCategoryFromUser()

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
        val newEditedTransaction = UiUtils.getTransactionEditFromUser(t)
        UiUtils.displayMessage("Transaction updated: ${newEditedTransaction.toString()}")

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