package managers

import models.Transaction
import saver.IFileManager
import java.util.UUID

class TransactionManager(private val fileManager: IFileManager) {

    fun deleteTransaction(uuid: UUID): Boolean {
         fileManager.getObjectById(uuid.toString(), Transaction::class.java) ?: return false
        fileManager.deleteObjectById(uuid, Transaction::class.java)
        return true
    }
}