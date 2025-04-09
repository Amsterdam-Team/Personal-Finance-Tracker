package test


fun main(){

//region Transactions Test Cases
//todo: write all test cases that related with transactions here :)
    //region add transaction test cases
    check(
        testName = "when amount is less than or equal zero number should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when amount is something else number should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when id of transaction is already associated with another transaction should return false",
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
    check(
        testName = "when date is empty should return false",
        result = false,
        acceptedResult = false
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
        testName = "when category is invalid like (numbers,special characters) should return false",
        result = false,
        acceptedResult = false
    )
    //endregion

    // region view transaction test cases
    check(
        testName = "when transaction id is not found then should return null",
        result= false,
        acceptedResult = false,
    )
    check(
        testName = "when transaction id is found then should return transaction",
        result= true,
        acceptedResult = true,
    )
    check(
        testName = "when transaction id is not valid then should return null",
        result= false,
        acceptedResult = false,
    )
    // endregion

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