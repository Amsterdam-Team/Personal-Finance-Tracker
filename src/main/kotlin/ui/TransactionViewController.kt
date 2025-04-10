package ui

import managers.CategoryManager
import managers.TransactionManager
import models.Category
import models.Transaction
import models.TransactionType
import saver.IFileManager
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import kotlin.random.Random
import kotlin.reflect.full.memberProperties

//val transactionManager: TransactionManager, val categoryManager: CategoryManager
class TransactionViewController (){
    fun addTransaction() {
        var transMap: MutableMap<String,Any> = mutableMapOf()

        for (property in Transaction:: class.memberProperties){
            if(property.name == "category"){
                // add category
                val category = addCategory()
                transMap["category"] = category

                continue
            }
            if (property.name == Transaction::id.name){
                transMap["id"] = Random.nextInt(0,9999)
                continue
            }
            if(property.name == "type"){
                var value = UiUtils.getUserInput("enter ${property.name}; choose one please: income, expense ")
                when{
                    value.lowercase() == TransactionType.INCOME.toString().lowercase() -> transMap["type"] = value.lowercase()
                    value.lowercase() == TransactionType.EXPENSE.toString().lowercase() -> transMap["type"] = value.lowercase()

                }
                continue
            }
            if (property.name == "date"){
                var value = UiUtils.getUserInput("enter ${property.name}; enter it in this format day/ month/ year or enter 'now' to indicate today")
                if (value == "now"){
                    transMap["date"]= LocalDate.now()

                }else{
                    do {
                        val dateTimePattern = "dd/MM/yyyy"
                        var value = UiUtils.getUserInput("enter ${property.name}; enter it in this format day/ month/ year or press enter to indicate today")
                        val date = LocalDate.parse(value, DateTimeFormatter.ofPattern(dateTimePattern))
                        // we need to handle exception if occured
                        transMap["date"] = date

                    }while (value.split("/").size != 3)

                }
                continue
            }
            var value = UiUtils.getUserInput("enter ${property.name}")
            transMap[property.name] = value
        }
        var transaction = Transaction(
            amount = transMap.get("amount").toString().toDouble(),
            id =  transMap.get("id") as Int,
            description = transMap.get("description").toString(),
            date = transMap.get("date") as LocalDate,
            category = transMap["category"] as Category,
            type = if (transMap.get("type") == "income")  TransactionType.INCOME else TransactionType.EXPENSE,
        )
        UiUtils.displayMessage("Transaction added")
        println(transaction)
    }

    fun addCategory():Category {
        val catName = UiUtils.getUserInput("Enter Category Name: ")
        val category = Category(
            id = Random.nextInt(0,9999),
            name = catName,
        )
        // here we need to add category
        // then display the next step if success or failure
        // if success return this catgory
        return category

    }
}