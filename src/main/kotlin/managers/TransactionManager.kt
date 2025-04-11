package managers

import com.sun.jdi.IntegerType
import models.Category
import models.Transaction
import models.TransactionType
import models.TransactionViewResult
import saver.IFileManager
import utils.ResultStatus
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*
import java.util.zip.DataFormatException

class TransactionManager(private val fileManager: IFileManager) {


    fun editTransaction(
        transaction: Transaction
    ): ResultStatus<String> {
        val transactions = fileManager.getAllObjects(Transaction::class.java)
        val categories = fileManager.getAllObjects(Category::class.java)

        if (listOf(
                isValidID(transactions, transaction.id),
                isValidDescription(transaction.description),
                isValidaType(transaction.type.toString()),
                isValidDate(transaction.date.toString()),
                isValidCategory(categories, transaction.category.name),
                isValidAmount(transaction.amount)
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

fun isValidID(transactions: List<Transaction>, id: UUID): ResultStatus<String> {



    if (id.toString().contains(" ") || id.toString().isBlank())
        return ResultStatus.Error("Invalid Id")
    else {
        val transaction = transactions.find { it.id == id }
        if (transaction == null)
            return ResultStatus.Error("Invalid Id")
    }

    return ResultStatus.Success("success")
}

fun isValidAmount(amount: Double?): ResultStatus<String> {


    if (amount != null && amount.toInt() > 0)
        return ResultStatus.Success("success")
    return ResultStatus.Error("Invalid Amount")
}

fun isValidCategory(categories: List<Category>, category: String): ResultStatus<String> {
    if (category.isBlank())
        return ResultStatus.Error("Invalid Category")
    else {
        val category = categories.find { it.name == category.trim() }
        if (category == null)
            return ResultStatus.Error("Invalid Category")
    }
    return ResultStatus.Success("success")

}

fun isValidaType(type: String): ResultStatus<String> {
    if (enumValues<TransactionType>().any { it.name.equals(type, ignoreCase = true) })
        return ResultStatus.Success("success")
    else
        return ResultStatus.Error("Invalid Type")
}

fun isValidDescription(input: String): ResultStatus<String> {
    val regex = Regex("^(?=.*[a-zA-Z]).*$")
    if (regex.matches(input) || input.isBlank())
        return ResultStatus.Success("success")
    return ResultStatus.Error("Invalid Description")
}

fun isValidDate(date: String): ResultStatus<String> {
    val formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy")
    if (date.isNotBlank()) {
        return try {
            LocalDate.parse(date, formatter)
            ResultStatus.Success("success")
        } catch (e: DateTimeParseException) {
            ResultStatus.Error("Invalid Date")
        }
    }
    return ResultStatus.Error("Invalid Date")
}


