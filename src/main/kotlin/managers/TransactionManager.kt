package managers

import models.Transaction
import models.TransactionType
import saver.IFileManager
import utils.ResultStatus

class TransactionManager(private val fileManager: IFileManager) {

    fun addTransaction(transaction: Transaction):ResultStatus<String>{
        if (!checkIsValidInputAmount(transaction.amount.toString())) ResultStatus.Error("please enter a valid amount number")
        if (!checkIsValidDescription(transaction.description)) ResultStatus.Error("please enter a valid description")
        if (!checkIsValidDate(transaction.date.toString())) ResultStatus.Error("please enter a valid date with that format : DD/MM/YYYY")
        if (!checkIsValidTransactionType(transaction.type.toString())) ResultStatus.Error("please enter one of these types only (INCOME,EXPENSE)")

        return ResultStatus.Success("Successfully added ur transaction with id : ${transaction.id}")
    }
}
fun checkIsValidInputAmount(amount:String):Boolean{
    if (amount.isBlank()){
        return false
    }else if (!amount.matches(Regex("^\\d*\\.?\\d+$"))){
        return false
    }else if(amount <= 0 .toString()) {
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
fun checkIsValidDate(date:String):Boolean{
    //valid date format "DD/MM/YYYY"
    if (date.isBlank()) return false

    val dateFormat =date.split("/")
    if (dateFormat.size != 3) return false

    val day = dateFormat[0]
    val month = dateFormat[1]
    val year = dateFormat[2]

    if (day !in "1".."31"){
        return false
    }else if (month !in "1".."12"){
        return false
    }else if (year.length !=4 || !year.all{ it.isDigit()} || year.startsWith("0")){
        return false
    }
    return true
}
fun checkIsValidTransactionType(transactionType: String):Boolean{
    if (transactionType.isBlank()){
        return false
    }else if (transactionType.uppercase() != (TransactionType.INCOME.toString()) || transactionType != (TransactionType.EXPENSE.toString())){
        return false
    }
    return true
}
