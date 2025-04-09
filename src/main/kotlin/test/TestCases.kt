package test


fun main(){

//region Transactions Test Cases

    //region Edit Transaction Test Cases

     check(
        testName = "When all inputs are valid and transaction exists, then return true",
        result = true,
        acceptedResult = true
    )

    check(
        testName = "when amount equals zero or negative number return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when amount is something else number should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when id of transaction is invalid should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when description is invalid like (numbers,special characters) should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when description is empty should return true",
        result = true,
        acceptedResult = true
    )
    check(
        testName = "when date is invalid should return false",
        result = false,
        acceptedResult = false
    )
    // set today date
    check(
        testName = "when date is empty should return true",
        result = true,
        acceptedResult = true
    )
    check(
        testName = "when transaction type is empty should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when category type is empty should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when category type is invalid return false",
        result = false,
        acceptedResult = false
    )
    //endregion

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