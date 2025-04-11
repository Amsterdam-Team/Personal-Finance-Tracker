package test

import utils.ResultStatus
import utils.Validator.isValidDate
import utils.Validator.isValidInput
import utils.Validator.isValidInputAmount
import utils.Validator.isValidTransactionType


fun main() {
//region Transactions Test Cases
//todo: write all test cases that related with transactions here :)

    //region add transaction test cases
    check(
        testName = "when amount is empty should return false",
        result = isValidInputAmount(""),
        acceptedResult = ResultStatus.Empty("please enter an amount")
    )
    check(
        testName = "when amount is less than or equal zero number should return false",
        result = isValidInputAmount("0"),
        acceptedResult = ResultStatus.Error("amount number should be more than zero")
    )
    check(
        testName = "when amount is letters or special characters should return false",
        result = isValidInputAmount("a&"),
        acceptedResult = ResultStatus.Error("amount number shouldn't contain any letters or special characters")
    )
    check(
        testName = "when description is invalid like (containing only numbers,special characters) should return false",
        result = isValidInput("1213$%#"),
        acceptedResult = ResultStatus.Error("input shouldn't be numbers or special characters only it must have a letters")
    )
    check(
        testName = "when description is empty should return true",
        result = isValidInput(""),
        acceptedResult = ResultStatus.Empty("please enter an input")
    )
    check(
        testName = "when date is invalid should return false",
        result = isValidDate("1-2-2024"),
        acceptedResult = ResultStatus.Error("please enter a valid date with that format : yyyy-MM-dd")
    )
    check(
        testName = "when date is empty should return false",
        result = isValidDate(""),
        acceptedResult = ResultStatus.Empty("please enter the date")
    )
    check(
        testName = "when transaction type is empty should return false",
        result = isValidTransactionType(""),
        acceptedResult = ResultStatus.Error("please enter a transaction type ")
    )
    check(
        testName = "when transaction type isn't one of these(INCOME,EXPENSE) should return false",
        result = isValidTransactionType("ahmed"),
        acceptedResult = ResultStatus.Error("please enter one of these types only (INCOME,EXPENSE)")
    )
    //endregion/

 //end region
}
fun <T> check(testName: String, result: T, acceptedResult: T) {
    if (result == acceptedResult) println("Success - $testName")
    else println("Fail - $testName")
}
