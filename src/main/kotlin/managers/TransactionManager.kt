package managers
import models.Transaction
import models.TransactionViewResult
import saver.IFileManager
import utils.ResultStatus
import java.util.UUID

class TransactionManager(private val fileManager: IFileManager) {
    fun viewTransactionById(inputId: String): TransactionViewResult {
        return when (val validationResult = validateUUID(inputId)) {
            is ResultStatus.Success -> {
                val uuid = validationResult.data
                try {
                    val transaction = fileManager.getObjectById(uuid.toString(), Transaction::class.java)
                    if (transaction != null) {
                        TransactionViewResult.Success(transaction)
                    } else {
                        TransactionViewResult.Error("Transaction not found.")
                    }
                } catch (e: Exception) {
                    TransactionViewResult.Error("Error reading the file: ${e.message}")
                }
            }
            is ResultStatus.Error -> TransactionViewResult.Error(validationResult.errorMessage)
            is ResultStatus.Empty -> TransactionViewResult.Error(validationResult.message)
        }
    }

    fun viewAllTransactions(): List<Pair<String, String>> {
        val transactions = fileManager.getAllObjects(Transaction::class.java)
        return transactions.map {
            Pair(it.id.toString(), it.description)
        }
    }

}

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
