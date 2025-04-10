package ui

class UiUtils {
    companion object {
        fun getUserInput(message: String, last: String = "\n"): String{
            var data = " "
            do {
                if (last.isEmpty()){
                    print(message)
                }else{
                    println(message)
                }

                data = readln()
            }while (data.isEmpty())

            return data
        }
        fun displayMessage(msg:String){
            println(msg)
        }
    }
}