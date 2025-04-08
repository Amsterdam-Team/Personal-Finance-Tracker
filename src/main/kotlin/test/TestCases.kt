package test


fun main(){
//region Transactions Test Cases
//todo: write all test cases that related with transactions here :)

//endregion

//region Category Test Cases
//todo: write all test cases that related with Category here :)

//endregion

//region Report Test Cases
//todo: write all test cases that related with Report here :)

//endregion
}

fun check(testName: String, result: Boolean, acceptedResult: Boolean){
    if (result == acceptedResult){
        println("Success - $testName")
    } else{
        println("Failed - $testName")
    }
}