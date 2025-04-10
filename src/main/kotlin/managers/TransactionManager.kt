package managers
import models.Transaction
import models.TransactionViewResult
import saver.IFileManager

class TransactionManager(private val fileManager: IFileManager) {
    fun viewTransactionById(id: Int): TransactionViewResult {
        if (id <= 0) {
            return TransactionViewResult.Error("Transaction ID is not Valid")
        }

        // Input is only spaces
        if (id.toString().trim().isEmpty()) {
            return TransactionViewResult.Error("Input is empty or contains only spaces.")
        }

        // Non-numeric input (letters or symbols)
        if (id.toString().toIntOrNull() == null) {
            return TransactionViewResult.Error("You must enter a valid number.")
        }

        try {
            val transaction = fileManager.getObjectById(Transaction::class.java, id)
            return TransactionViewResult.Success(transaction)
        } catch (e: Exception) {
            return TransactionViewResult.Error("Error reading the file: ${e.message}")
        }
//        val transaction = fileManager.getObjectById(Transaction::class.java, id)
//
//        return TransactionViewResult.Success(transaction)
    }

}