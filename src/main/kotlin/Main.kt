import managers.Parser
import models.Category
import models.Transaction
import models.TransactionType
import saver.FileManagerImpl
import java.time.LocalDate
import java.util.UUID

fun main(args: Array<String>) {

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    val fileManager: FileManagerImpl = FileManagerImpl()
    val category = Category(5,"food")
//    fileManager.saveObject(Transaction(
//        id = 5,
//        amount = 5.0,
//        description = "",
//        category = category,
//        date = LocalDate.now(),
//        type = TransactionType.INCOME
//    ))
//    fileManager.saveObject(category)

    println(fileManager.getAllObjects(Category::class))
    fileManager.deleteObjectById(8)
    println(fileManager.getAllObjects(Category::class))

}


