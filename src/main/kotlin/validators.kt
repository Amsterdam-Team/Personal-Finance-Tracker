import models.TransactionType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object Validators {
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
}