package ui

object ApplicationView {
    fun start(){
        var name = " "
        do {
            println("Hello :), please enter your name")
            name = readln()
        }while (name.isEmpty())

        println("Hello :) $name")
        showAvailableCommands()

        var userCommand = getUserInput("Enter specific command")
        println("you submit command: ${userCommand.toIntOrNull()} ")
        println("we are processing your command, please wait...")
        println("by by")

    }

    fun showAvailableCommands(){
        println("Available commands:")
        println("press 1 for add transaction")
        println("press 2 for view transaction")
        println("press 3 for delete transaction")
        println("press 4 for edit transaction")

    }
    private  fun getUserInput(message: String): String{
        var data = " "
        do {
            println(message)
            data = readln()
        }while (data.isEmpty())

        return data
    }

}