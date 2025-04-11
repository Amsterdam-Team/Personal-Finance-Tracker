package managers


import models.Category
import models.Transaction
import models.TransactionType
import saver.IFileManager
import utils.ResultStatus

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*


class TransactionManager(private val fileManager: IFileManager) {


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
                isValidaType(transaction.type.toString()),
                isValidDate(transaction.date.toString()),
                isValidCategory( transaction.category.name),
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

fun isValidID(id: UUID): ResultStatus<String> {



    if (id.toString().contains(" ") || id.toString().isBlank())
        return ResultStatus.Error("Invalid Id")
    return ResultStatus.Success("success")
}

fun isValidAmount(amount: Double?): ResultStatus<String> {


    if (amount != null && amount > 0.0)
        return ResultStatus.Success("success")
    return ResultStatus.Error("Invalid Amount")
}

fun isValidCategory (category: String): ResultStatus<String> {
    if (category.isBlank())
        return ResultStatus.Error("Invalid Category")
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


