package ui

import models.Category
import models.Transaction
import models.TransactionType
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

import kotlin.reflect.full.memberProperties

class UiUtils {
    companion object {
        fun getTransactionFromUser(transId : UUID = UUID.randomUUID()): Transaction{
            val transMap: MutableMap<String,Any> = mutableMapOf()

            for (property in Transaction:: class.memberProperties){
                if (property.name =="id"){
                    continue
                }
                if(property.name == "amount"){
                    // add category


                    transMap["amount"] = getAmountValue()

                    continue
                }
                if(property.name == "category"){
                    // add category
                    val category = getCategoryFromUser()
                    transMap["category"] = category

                    continue
                }

                if(property.name == "type"){
                    val value = getUserInput("enter ${property.name}; choose one please: income, expense ")
                    when{
                        value.lowercase() == TransactionType.INCOME.toString().lowercase() -> transMap["type"] = value.lowercase()
                        value.lowercase() == TransactionType.EXPENSE.toString().lowercase() -> transMap["type"] = value.lowercase()

                    }
                    continue
                }
                if (property.name == "date"){
                    transMap["date"] = getDateFromUser()
                    continue
                }
                val value = getUserInput("enter ${property.name}")
                transMap[property.name] = value
            }
            val transaction = Transaction(
                amount = transMap["amount"].toString().toDouble(),
                id =  transId,
                description = transMap["description"].toString(),
                date = transMap["date"] as LocalDate,
                category = transMap["category"] as Category,
                type = if (transMap["type"] == "income")  TransactionType.INCOME else TransactionType.EXPENSE,
            )

            return transaction
        }

         fun getTransactionEditFromUser(oldTrans: Transaction): Transaction{
            val transMap:MutableMap<String,Any> = mutableMapOf()

            for (property in Transaction::class.memberProperties){
                if (property.name == "id"){
                    transMap["id"] = oldTrans.id
                    continue
                }
                if (property.name == "category"){
                    val category = getCategoryFromUser(categoryId = oldTrans.category.id, oldName = oldTrans.category.name)
                    transMap["category"] = category
                    continue
                }
                if (property.name == "date"){
                    transMap["date"] = getDateFromUser(oldDate = oldTrans.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString())
                    continue
                }
                if (property.name == "amount"){
                    val amount = getAmountValue(oldTrans.amount)
                    transMap["amount"] = amount
                    continue
                }
               if (property.name == "description"){
                   val description = getUserInput("enter a new description ", oldTrans.description)
                   transMap["description"] = description
                   continue
               }
                if (property.name == "type"){
                    val type = getUserInput("enter a new transaction type ", oldTrans.type.toString())
                    transMap["type"] = type.lowercase()
                    continue
                }

            }
            val transaction = Transaction(
                amount = transMap["amount"].toString().toDouble(),
                id =  transMap["id"] as UUID,
                description = transMap["description"].toString(),
                date = transMap["date"] as LocalDate,
                category = transMap["category"] as Category,
                type = if (transMap["type"] == "income")  TransactionType.INCOME else TransactionType.EXPENSE,
            )
            return transaction

        }
        fun getCategoryFromUser(categoryId: UUID = UUID.randomUUID(), oldName:String? = null ): Category {
            if (oldName != null){
                val catName = getUserInput("Enter new Category Name: ", data = oldName)
                val category = Category(
                    id = categoryId,
                    name = catName,
                )
                return category
            }
            val catName = getUserInput("Enter Category Name: ")
            val category = Category(
                id = categoryId,
                name = catName,
            )

            return category

        }

        private fun getAmountValue(amount: Double? = null):Double {
            var value: String
            do {
                value = if (amount != null) getUserInput("your amount is $amount enter the new amount") else getUserInput("enter amount")

            }while (value.toDoubleOrNull() == null)
            // here we need to add category
            // then display the next step if success or failure
            // if success return this category
            return value.toDouble()

        }
        fun getUserInput(message: String, data: String?=null, last: String = "\n"):String {
            var value: String
            if (data != null){
                do {
                    print("previous value: $data, ")
                    println(message)

                    value = readln()
                }while (value.isEmpty())
            }else{
                do {
                    if (last.isEmpty()){
                        print(message)
                    }else{
                        println(message)
                    }

                    value = readln()

                }while ( value.isEmpty())
            }



            return value
        }
        fun displayMessage(msg:String){
            println(msg)
        }

         fun getDateFromUser(oldDate:String? = null, msg:String = "enter date here; enter it in this format day/ month/ year or enter 'now' to indicate today"): LocalDate{
            if (oldDate != null){
                println("the old date is $oldDate, ")
            }
            var date: LocalDate? = LocalDate.now()
            var value = getUserInput(message =  msg)
            if (value == "now"){
                return date!!

            }else{
                println(value)
                do {
                    val dateTimePattern = "d/M/yyyy"
                     date = try {
                         LocalDate.parse(value, DateTimeFormatter.ofPattern(dateTimePattern))
                     } catch (e :Exception){
                         println("invalid date format; use this format (day/moth/year)")
                         null
                     }
                    // we need to handle exception if not occurred return date


                }while (value.split("/").size != 3 || date==null)

            }
            return date!!
        }

         fun getCategoryUuid(msg: String) :UUID{
            var categoryUuid: UUID?
            do {
                var categoryId = getUserInput(msg)
                categoryUuid = try {
                    UUID.fromString(categoryId)
                } catch (e: Exception) {
                    null

                }
                if (categoryUuid == null){
                    println("invalid uuid; please enter valid uuid")
                }
            }while (categoryUuid == null)
            return categoryUuid
        }
        fun getUserUUID(): UUID{
            var transUuid: UUID?
            do {
                var trnasId = UiUtils.getUserInput("enter the transaction id ")
                transUuid = try {
                    UUID.fromString(trnasId)
                } catch (e: Exception) {
                    null

                }
                if (transUuid == null){
                    println("invalid uuid; please enter valid uuid")
                }
            }while (transUuid == null)
            return transUuid
        }
    }
}