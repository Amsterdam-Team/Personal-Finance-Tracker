package managers

import models.Transaction
import saver.IFileManager
import utils.ResultStatus
import java.util.UUID

class TransactionManager(private val fileManager: IFileManager) {


    // delete transaction by ibrahim
    fun deleteTransactionByUUID(id: UUID): Boolean {
        return try {
            val transactions = fileManager.getAllObjects(Transaction::class.java).toMutableList()
                ?: return false
            if (transactions.isEmpty()) return false
            transactions.find { it.id == id } ?: return false
            fileManager.deleteObjectById(id, Transaction::class.java)
            return true
        } catch (e: IllegalArgumentException) {
            false
        } catch (e: Exception) {
            false
        }
    }
}