import models.Category
import models.Transaction
import models.TransactionType
import utils.ActionStatus
import java.time.LocalDate

fun main(args: Array<String>) {

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    //template add transaction
    val transactionList = mutableListOf<Transaction>()

    val transaction = Transaction(
        id = 1,
        amount = 100.0,
        description = "Grocery shopping",
        date = LocalDate.now(),
        category = Category(
            id = 1, name = "Category"
        ),
        type = TransactionType.EXPENSE
    )




    fun addTransaction(transaction: Transaction): ActionStatus<Transaction> {
        return try {
            // You can work here, for example, from the authenticity of the data
            if (transaction.amount <= 0.0) {
                return ActionStatus.Error("Amount must be greater than zero")
            }
            transactionList.add(transaction)
            ActionStatus.Success(transaction)
        } catch (e: Exception) {
            ActionStatus.Error("Failed to add transaction: ${e.message}")
        }
    }

    val result = addTransaction(transaction)

    when (result) {
        is ActionStatus.Success -> println("Transaction added: ${result.data}")
        is ActionStatus.Error -> println("Error: ${result.errorMessage}")
        is ActionStatus.InProgress -> println("Adding transaction...")
        is ActionStatus.Empty -> println("No data.")
    }

    // 1. amount <= 0
    val case1 = transaction.copy(amount = 0.0)
    test.check(
        testName = "when amount is less than or equal zero number should return false",
        result = addTransaction(case1),
        acceptedResult = false
    )
}


