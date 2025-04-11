package managers


import models.Category
import models.Transaction

import saver.IFileManager
import utils.ResultStatus
import utils.Validator.isValidDate
import utils.Validator.isValidDescription
import utils.Validator.isValidID
import utils.Validator.isValidInput
import utils.Validator.isValidInputAmount
import utils.Validator.isValidTransactionType
import java.util.*

class TransactionManager(private val fileManager: IFileManager) {
    fun deleteTransaction(uuid: UUID): ResultStatus<String> {
        fileManager.getObjectById(uuid.toString(), Transaction::class.java) ?: return ResultStatus.Error(
            "Transaction not found"
        )
        fileManager.deleteObjectById(uuid, Transaction::class.java)
        return ResultStatus.Success("true")
    }
    fun viewTransactionById(inputId: String): ResultStatus<Transaction> {
        return when (val validationResult = validateUUID(inputId)) {
            is ResultStatus.Success -> {
                val uuid = validationResult.data
                try {
                    val transaction = fileManager.getObjectById(uuid.toString(), Transaction::class.java)
                    if (transaction != null) {
                        ResultStatus.Success(transaction)
                    } else {
                        ResultStatus.Error("Transaction not found.")
                    }
                } catch (e: Exception) {
                    ResultStatus.Error("Error reading the file: ${e.message}")
                }
            }
            is ResultStatus.Error -> ResultStatus.Error(validationResult.errorMessage)
            is ResultStatus.Empty -> ResultStatus.Error(validationResult.message)
        }
    }


    fun viewAllTransactions(): ResultStatus<List<Transaction>> {
        val transactions = fileManager.getAllObjects(Transaction::class.java)
        if (transactions.isEmpty()){
            return ResultStatus.Empty("No Transactions yet.")
        }
        return ResultStatus.Success(transactions)
    }


// TODO Moved this function based on the team's feedback
private fun validateUUID(input: String): ResultStatus<UUID> {
    val trimmed = input.trim()
    if (trimmed.isEmpty()) {
        return ResultStatus.Error("Input is empty or contains only spaces.")
    }

    return try {
        ResultStatus.Success(UUID.fromString(trimmed))
    } catch (e: IllegalArgumentException) {
        ResultStatus.Error("You must enter a valid UUID.")
    }
}

    fun addTransaction(transaction: Transaction): ResultStatus<String> {
        var availableCategories = fileManager.getAllObjects(Category::class.java).filter {
            transaction.category.name == it.name
        }
        if (availableCategories.isEmpty()) {
            fileManager.saveObject(transaction.category)
        }
                fileManager.saveObject(transaction)
                return ResultStatus.Success("Successfully added ur transaction with id : ${transaction.id}")
            }






    fun editTransaction(
        transaction: Transaction
    ): ResultStatus<String> {
        val transactionFromFile = fileManager.getObjectById(transaction.id.toString(),Transaction::class.java)

        val categoryFromFile = fileManager.getObjectById(transaction.category.id.toString(),Category::class.java)

        if(transactionFromFile == null || categoryFromFile == null)
            return ResultStatus.Error("Invalid Data")

        if (listOf(
                isValidID(transaction.id),
                isValidDescription(transaction.description),
                isValidTransactionType(transaction.type.toString()),
                isValidInput( transaction.category.name),
                isValidInputAmount(transaction.amount.toString())
            ).all { it == ResultStatus.Success("success") }
        ) {
            fileManager.deleteObjectById(transaction.id,Transaction::class.java)
            fileManager.saveObject(transaction)
            return ResultStatus.Success("Transaction Edited Successfully")
        } else {
            return ResultStatus.Error("Invalid Data")
        }


    }
}

