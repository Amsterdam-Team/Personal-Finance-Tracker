package test


fun main(){
//region Transactions Test Cases
//todo: write all test cases that related with transactions here :)

//endregion

//region Category Test Cases
//todo: write all test cases that related with Category here :)

//endregion

//region Report Test Cases
    check(
        testName = "1. When there are no transactions in the date range, should return zero income, zero expenses, and zero net balance",
        expectedResult = "",
        actualResult = ""
    )

    check(
        testName = "2. When only income transactions exist in the date range, should return correct income total and zero expenses",
        expectedResult = "",
        actualResult = ""
    )

    check(
        testName = "3. When only expense transactions exist in the date range, should return correct expense total and zero income",
        expectedResult = "",
        actualResult = ""
    )

    check(
        testName = "4. When both income and expense transactions exist, should return correct totals and net balance",
        expectedResult = "",
        actualResult = ""
    )

    check(
        testName = "5. When start date is after end date, should return null",
        expectedResult = null,
        actualResult = ""
    )

    check(
        testName = "6. When start or end date is invalid should return null",
        expectedResult = null,
        actualResult = ""
    )

//endregion
}

fun <T> check(testName: String, expectedResult: T, actualResult: T) {
    if(expectedResult == actualResult) println("Success - $testName")
    else println("Fail - $testName")
}