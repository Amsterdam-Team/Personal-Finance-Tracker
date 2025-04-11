package utils

import models.TransactionType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object Validator {
    fun isValidInputAmount(amount:String):ResultStatus<String>{
        if (amount.isBlank()){
            return ResultStatus.Empty("please enter an amount")
        }else if (!amount.matches(Regex("^\\d*\\.?\\d+$"))){
            return ResultStatus.Error("amount number shouldn't contain any letters or special characters")
        }else if(amount <= 0.0 .toString()) {
            return ResultStatus.Error("amount number should be more than zero")
        }
         return ResultStatus.Success("Successfully added ur amount $amount")
    }
    fun isValidInput(input:String):ResultStatus<String>{
        if (input.isBlank()){
            return ResultStatus.Empty("please enter an input")
        }else if (!input.any{it.isLetter()}){
            return ResultStatus.Error("input shouldn't be numbers or special characters only it must have a letters")
        }
        return ResultStatus.Success("Successfully added ur input $input")
    }
    fun isValidDate(inputDate:String):ResultStatus<String>{
        if (inputDate.isBlank()) ResultStatus.Empty("please enter the date")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return try {
            val date = LocalDate.parse(inputDate, formatter)
        ResultStatus.Success("Successfully added ur $date")
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
            ResultStatus.Success("Successfully added ur transaction type $transactionType")
        }
    }
}