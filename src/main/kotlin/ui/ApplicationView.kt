package ui

import java.lang.Thread.sleep
import java.util.*

class ApplicationView (val transController :TransactionViewController, val reportController: ReportViewController, val categoryController: CategoryViewController) {
    fun start(){
        var name = " "
        do {
            println("Hello :), please enter your name")
            name = readln()
        }while (name.isEmpty())

        println("Hello :) $name")
        showAvailableCommands()

        var userCommand = UiUtils.getUserInput("Enter specific command")
        handleUserCommand(userCommand.toString())


    }




     private fun handleUserCommand(command:String){
        when(command.lowercase()){
            "t" -> handleTransactionCommand()
            "exit" -> {
                println("see you soon, bye")
                return
            }
            "ms"-> {
                handleMonthlySummary()
            }
            "c" -> {
                handleCategoryCommand()
            }
            "br" ->{
                handleBalanceReport()
            }
            else -> {
                showAvailableCommands()
                var userCommand = UiUtils.getUserInput("Enter specific command")
                handleUserCommand(userCommand.toString())
            }

        }

    }
    private fun handleCategoryCommand(){
        showCategoryCommand()
        val command = UiUtils.getUserInput("enter command here: ", last = " ").toIntOrNull() ?: handleTransactionCommand()
        when(command){
            1 -> {
                val category = UiUtils.getCategoryFromUser()
                categoryController.addCategory(category)
                reshowMajorCommands()
            }
            2 -> {
                categoryController.viewAllCategories()
                reshowMajorCommands()
            }
            4 -> {

                val categoryUuid =UiUtils.getCategoryUuid("enter the category id you want to edit ")
                categoryController.editCategory(categoryUuid)
                reshowMajorCommands()
            }
            3 -> {
                categoryController.viewAllCategories()
                val categoryUuid =UiUtils.getCategoryUuid("enter the category id you want to delete ")
                categoryController.deleteCategory(categoryUuid)
                reshowMajorCommands()
            }
            else -> {
                println("enter valid category command")
                handleTransactionCommand()

            }
        }
    }
    private fun handleTransactionCommand(){
        showTransCommand()
        val command = UiUtils.getUserInput("enter command here: ", last = " ").toIntOrNull() ?: handleTransactionCommand()
        when(command){
            1 -> {
                transController.addTransaction()
                reshowMajorCommands()
            }
            2 -> {
                transController.getAllTransactions()
                reshowMajorCommands()
            }
            5 ->{
                transController.getAllTransactions()
                val id = UiUtils.getUserUUID()
                transController.getTransactionById(id)
                reshowMajorCommands()
            }
            4 -> {
                transController.getAllTransactions()
               val transactionUuid =UiUtils.getUserUUID()
                transController.editTransaction(transactionUuid)
                reshowMajorCommands()
            }
            3 -> {
                transController.getAllTransactions()
                val transactionUUID =UiUtils.getUserUUID()
                transController.deleteTransaction(transactionUUID)
                reshowMajorCommands()
            }
            else -> {
                println("enter valid transaction command")
                handleTransactionCommand()

            }
        }
    }
    private fun handleBalanceReport(){
        val startDate = UiUtils.getDateFromUser(msg = "enter a start date to get the balance report; enter it in this format year-month-day ")
        reportController.getBalanceReport(startDate)
        reshowMajorCommands()

    }
    private fun handleMonthlySummary(){
        var month :Int?
        do {
           month = UiUtils.getUserInput("Enter specific month", last = " ").toIntOrNull()

        }while (month !in 1..12 || month == null)

        var year :Int?

        do {
            year = UiUtils.getUserInput("Enter specific year", last = " ").toIntOrNull()

        }while (year == null)


        reportController.getMonthlySummary(month=month, year=year)
        reshowMajorCommands()


    }

    private fun reshowMajorCommands(){
        showAvailableCommands()
        var userCommand = UiUtils.getUserInput("Enter specific command")
        handleUserCommand(userCommand.toString())
    }

    fun showAvailableCommands(){
        sleep(2000)
        println("Available commands:")
        println("T -> transaction commands")
        println("C -> Category commands")
        println("MS -> Monthly Summary")
        println("BR -> Balance Report")
        println("Exit -> exit")



    }
    private fun showTransCommand(){
        println("Transaction commands: ")
        println("press 1 for add transaction")
        println("press 2 for view all transactions")
        println("press 3 for delete transaction")
        println("press 4 for edit transaction")
        println("press 5 for view single transaction by Id ")


    }
    private fun showCategoryCommand(){
        println("Category commands: ")
        println("press 1 for add category")
        println("press 2 for view all categories")
        println("press 3 for delete category")
        println("press 4 for edit category")


    }
}