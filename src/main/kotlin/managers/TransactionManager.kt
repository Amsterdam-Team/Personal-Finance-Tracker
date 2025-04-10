package managers

import models.TransactionType
import saver.IFileManager

class TransactionManager(private val fileManager: IFileManager) {

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
    val dateFormat =date.split("/")
    if (date.isBlank()) return false
    if (dateFormat[0] !in "1".."31"){
        return false
    }else if (dateFormat[1] !in "1".."12"){
        return false
    }else if (dateFormat[2].length !=4 || !dateFormat[2].all{ it.isDigit()} || dateFormat[2].startsWith("0")){
        return false
    }
    return true
}
fun checkIsValidTransactionType(transactionType: String):Boolean{
    if (transactionType.isBlank()){
        return false
    }else if (transactionType != (TransactionType.INCOME.toString()) || transactionType != (TransactionType.EXPENSE.toString())){
        return false
    }
    return true
}
