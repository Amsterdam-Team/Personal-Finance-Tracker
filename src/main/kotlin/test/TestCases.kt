package test

import models.Transaction


fun main() {

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
    //region delete transaction test cases
    check(
        testName = "when there is no any transaction added before should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when entered id is anything except the numbers should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when entered id doesn't exist in the transactions should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when entered id is less than zero should return false",
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


//region Monthly Summary Test Cases

    check(testName = "when no transactions in month should return NoTransactions", result = false, acceptedResult = false)
    check(testName = "when year is after now should return error", result = false, acceptedResult = false)
    check(testName = "when month is after current month in current year should return error", result = false, acceptedResult = false)
    check(testName = "when month number is invalid should return error", result = false, acceptedResult = false)
    check(testName = "when year number is invalid should return error", result = false, acceptedResult = false)
    check(testName = "when valid month with transactions should return correct summary", result = false, acceptedResult = false)

//endregion
}

fun check(testName: String, result: Boolean, acceptedResult: Boolean) {
    if (result == acceptedResult) {
        println("Success - $testName")
    } else {
        println("Failed - $testName")
    }
}