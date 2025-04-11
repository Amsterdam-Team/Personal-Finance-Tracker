package ui

import java.util.*

class ApplicationView (val transController :TransactionViewController) {
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

    fun showAvailableCommands(){
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
        println("press 2 for view transaction")
        println("press 3 for delete transaction")
        println("press 4 for edit transaction")


    }


     fun handleUserCommand(command:String){
        when(command.lowercase()){
            "T".lowercase() -> handleTransactionCommand()
            "exit".lowercase() -> {
                println("see you soon, bye")
                return
            }

        }
    }
    fun handleTransactionCommand(){
        showTransCommand()
        val command = UiUtils.getUserInput("enter command here: ", last = " ").toInt()
        when(command){
            1 -> transController.addTransaction()
            2 -> transController.getAllTransactions()
            4 -> {

                var transUuid: UUID?
                do {
                    var trnasId = UiUtils.getUserInput("enter the transaction id ")
                     transUuid = try {
                        UUID.fromString(trnasId)
                    } catch (e: Exception) {
                        null
                    }
                }while (transUuid == null)
                transController.editTransaction(transUuid)
            }
        }
    }

}