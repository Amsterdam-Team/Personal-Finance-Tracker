package ui

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
        }
    }
    fun handleTransactionCommand(){
        showTransCommand()
        val command = UiUtils.getUserInput("enter command here: ", last = " ").toInt()
        when(command){
            1 -> transController.addTransaction()
        }
    }

}