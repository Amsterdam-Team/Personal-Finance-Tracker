package managers

import Validators.isValidCategory
import Validators.isValidDate
import Validators.isValidDescription
import Validators.isValidID
import Validators.isValidInputAmount
import Validators.isValidTransactionType
import models.Category
import models.Transaction

import saver.IFileManager
import utils.ResultStatus
import java.util.*

class TransactionManager(private val fileManager: IFileManager) {
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

    fun viewAllTransactions(): List<Pair<String, String>> {
        val transactions = fileManager.getAllObjects(Transaction::class.java)
        return transactions.map {
            Pair(it.id.toString(), it.description)
        }
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

        return when {
            !isValidInputAmount(transaction.amount.toString())
                -> ResultStatus.Error("please enter a valid amount number")

            !isValidDescription(transaction.description)
                -> ResultStatus.Error("please enter a valid description")

            !isValidDate(transaction.date.toString())
                -> ResultStatus.Error("please enter a valid date with that format : yyyy-MM-dd")

            !isValidTransactionType(transaction.type.toString())
                -> ResultStatus.Error("please enter one of these types only (INCOME,EXPENSE)")

            else -> {
                fileManager.saveObject(transaction)
                return ResultStatus.Success("Successfully added ur transaction with id : ${transaction.id}")
            }
        }

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
                isValidDate(transaction.date.toString()),
                isValidCategory( transaction.category.name),
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

