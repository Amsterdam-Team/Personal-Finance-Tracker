package utils

import models.Category
import models.TransactionType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

object Validator {
    fun isValidCategoryID(categories: List<Category>, id: UUID): ResultStatus<String> {
        if (id.toString().isBlank() || id.toString().contains(" "))
            return ResultStatus.Error("Invalid Id")
        else {
            val category = categories.find { it.id == id }
            if (category == null)
                return ResultStatus.Success("success")
        }
        return ResultStatus.Error("Id Already Exists")
    }
    fun isValidInputAmount(amount:String):ResultStatus<String>{
        if (amount.isBlank()){
            return ResultStatus.Empty("please enter an amount")
        }else if (!amount.matches(Regex("^\\d*\\.?\\d+$"))){
            return ResultStatus.Error("amount number shouldn't contain any letters or special characters")
        }else if(amount <= 0.0 .toString()) {
            return ResultStatus.Error("amount number should be more than zero")
        }
         return ResultStatus.Success("success")
    }
    fun isValidInput(input:String):ResultStatus<String>{
        if (input.isBlank()){
            return ResultStatus.Empty("please enter an input")
        }else if (!input.any{it.isLetter()}){
            return ResultStatus.Error("input shouldn't be numbers or special characters only it must have a letters")
        }
        return ResultStatus.Success("success")
    }
    fun isValidDate(inputDate:String):ResultStatus<LocalDate>{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return try {
            val date = LocalDate.parse(inputDate, formatter)
        ResultStatus.Success(date)
        } catch (e: DateTimeParseException) {
            ResultStatus.Error("please enter a valid date with that format : yyyy-MM-dd")
        }
    }
    fun isValidTransactionType(transactionType: String):ResultStatus<String>{
        return if (transactionType.isBlank()){
            ResultStatus.Error("please enter a transaction type ")
        }else if (transactionType.uppercase() != TransactionType.INCOME.toString() &&
            transactionType.uppercase() != TransactionType.EXPENSE.toString() ){
            ResultStatus.Error("please enter one of these types only (INCOME,EXPENSE)")
        }else{
            ResultStatus.Success("success")
        }
    }
    fun isValidID(id: UUID): ResultStatus<String> {
        if (id.toString().contains(" ") || id.toString().isBlank())
            return ResultStatus.Error("Invalid Id")
        return ResultStatus.Success("success")
    }
    fun isValidCategoryName(categories: List<Category>, name: String): ResultStatus<String> {
        if (name.isBlank() || name.contains(' '))
            return ResultStatus.Error("Invalid Name")
        else {
            if (name.matches(Regex("^[a-zA-Z ]+$"))) {
                val category = categories.find { it.name.equals(name, ignoreCase = true)}
                if (category == null)
                    return ResultStatus.Success("success")
            }
        }
        return ResultStatus.Error("Invalid Name")
    }
}