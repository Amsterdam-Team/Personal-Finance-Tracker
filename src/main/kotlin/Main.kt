import managers.Parser
import models.Category
import models.Transaction
import models.TransactionType
import saver.FileManagerImpl
import java.time.LocalDate
import java.util.UUID

fun main(args: Array<String>) {


    val fileManager: FileManagerImpl = FileManagerImpl()

//    println(fileManager.getAllObjects(Category::class.java))
//    println(fileManager.getAllObjects(Transaction::class.java))
//
//    println(fileManager.getObjectById("5360e12b-46c4-428a-961e-9321e00c2c71", Category::class.java))
//    fileManager.deleteObjectById(UUID.fromString("7723aab6-485d-4f2e-9cf5-60e27e5b21d5"), Transaction::class.java)
}


