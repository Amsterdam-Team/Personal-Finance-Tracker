package managers

import models.Transaction
import models.TransactionType
import saver.FileManagerImpl
import utils.ResultStatus
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun main(){
    println()
}
class TransactionManager(private val fileManager: FileManagerImpl) {

    fun addTransaction(transaction: Transaction):ResultStatus<String>{
        return if (!checkIsValidInputAmount(transaction.amount.toString())){
            ResultStatus.Error("please enter a valid amount number")
        }else if (!checkIsValidDescription(transaction.description)){
            ResultStatus.Error("please enter a valid description")
        }else if(!checkIsValidDate(transaction.date.toString())) {
            ResultStatus.Error("please enter a valid date with that format : DD/MM/YYYY")
        }else if (!checkIsValidTransactionType(transaction.type.toString())){
            println(transaction.type.toString())
            ResultStatus.Error("please enter one of these types only (INCOME,EXPENSE)")
        }
        else{
            fileManager.saveObject(transaction)
            ResultStatus.Success("Successfully added ur transaction with id : ${transaction.id}")
        }
    }
}
fun checkIsValidInputAmount(amount:String):Boolean{
    if (amount.isBlank()){
        return false
    }else if (!amount.matches(Regex("^\\d*\\.?\\d+$"))){
        return false
    }else if(amount <= 0.0 .toString()) {
        return false
    }
    return true
}
fun checkIsValidDescription(description:String):Boolean{
    if (description.isBlank()){
        return true
    }else if (!description.any{it.isLetter()}){
        return false
    }
    return true
}
fun checkIsValidDate(inputDate:String):Boolean{

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return try {
        val date = LocalDate.parse(inputDate, formatter)
        date.format(formatter) == inputDate
    } catch (e: DateTimeParseException) {
        false
    }


}
fun checkIsValidTransactionType(transactionType: String):Boolean{
    return if (transactionType.isBlank()){
        false
    }else if (transactionType.uppercase() != TransactionType.INCOME.toString() &&
        transactionType.uppercase() != TransactionType.EXPENSE.toString() ){
        false
    }else{
        true
    }
}
