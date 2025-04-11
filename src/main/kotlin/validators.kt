import models.Category
import models.TransactionType
import utils.ResultStatus
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

object Validators {
    fun isValidInputAmount(amount:String):Boolean{
        if (amount.isBlank()){
            return false
        }else if (!amount.matches(Regex("^\\d*\\.?\\d+$"))){
            return false
        }else if(amount <= 0.0 .toString()) {
            return false
        }
        return true
    }
    fun isValidDescription(description:String):Boolean{
        if (description.isBlank()){
            return true
        }else if (!description.any{it.isLetter()}){
            return false
        }
        return true
    }
    fun isValidDate(inputDate:String):Boolean{

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return try {
            val date = LocalDate.parse(inputDate, formatter)
            date.format(formatter) == inputDate
        } catch (e: DateTimeParseException) {
            false
        }


    }
    fun isValidTransactionType(transactionType: String):Boolean{
        return if (transactionType.isBlank()){
            false
        }else if (transactionType.uppercase() != TransactionType.INCOME.toString() &&
            transactionType.uppercase() != TransactionType.EXPENSE.toString() ){
            false
        }else{
            true
        }
    }
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

    fun isValidCategoryName(categories: List<Category>, name: String): ResultStatus<String> {
        if (name.isBlank() || name.contains(' '))
            return ResultStatus.Error("Invalid Name")
        else {
            if (name.matches(Regex("^[a-zA-Z ]+$"))) {
                val category = categories.find { it.name == name }
                if (category == null)
                    return ResultStatus.Success("success")
            }
        }
        return ResultStatus.Error("Invalid Name")
    }
    fun isValidID(id: UUID): ResultStatus<String> {



        if (id.toString().contains(" ") || id.toString().isBlank())
            return ResultStatus.Error("Invalid Id")
        return ResultStatus.Success("success")
    }
    fun isValidCategory (category: String): ResultStatus<String> {
        if (category.isBlank())
            return ResultStatus.Error("Invalid Category")
        return ResultStatus.Success("success")

    }




}