package managers

import models.Transaction
import saver.IFileManager
import utils.ResultStatus
import java.util.UUID

class TransactionManager(private val fileManager: IFileManager) {

    fun deleteTransaction(uuid: UUID): Any {
         fileManager.getObjectById(uuid.toString(), Transaction::class.java) ?: return ResultStatus.Error(
             "Transaction not found"
         )
        fileManager.deleteObjectById(uuid, Transaction::class.java)
        return ResultStatus.Success(true)
    }
}