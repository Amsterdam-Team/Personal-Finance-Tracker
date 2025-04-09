package test

import models.Category
import models.Transaction
import models.TransactionType
import java.time.LocalDate


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

//endregion

//region Category Test Cases
//todo: write all test cases that related with Category here :)

//endregion

//region Report Test Cases
//todo: write all test cases that related with Report here :)

//endregion


//region Monthly Summary Test Cases

    fun getMonthlySummary(year:Int, month:Int,transactions:List<Transaction> ):MonthlySummaryResult{
        return MonthlySummaryResult.NoTransactions
    }


    fun checkMonthlySummary(testName: String, result: MonthlySummaryResult, expected: MonthlySummaryResult) {
        val passed = when {
            result is MonthlySummaryResult.Error && expected is MonthlySummaryResult.Error ->
                result.errorMessage == expected.errorMessage
            result is MonthlySummaryResult.NoTransactions && expected is MonthlySummaryResult.NoTransactions -> true
            result is MonthlySummaryResult.Success && expected is MonthlySummaryResult.Success ->
                result.transactions == expected.transactions
            else -> false
        }

        if (passed) {
            println("Success - $testName")
        } else {
            println("Failed - $testName")
        }
    }


    val carCategory = Category(id = 1, name = "car")
    val salaryCategory = Category(id = 1, name = "salary")
    val rentCategory = Category(id = 1, name = "rent")

    val testTransactions = listOf(
        Transaction(1, 5000.0, "Salary", LocalDate.of(2023,6,1), salaryCategory, TransactionType.INCOME),
        Transaction(2, 200.0, "fuel", LocalDate.of(2023,6,5), carCategory, TransactionType.EXPENSE),
        Transaction(3, 1000.0, "Rent", LocalDate.of(2023,5,1), rentCategory, TransactionType.EXPENSE)
    )

    checkMonthlySummary(
        "when no transactions in month should return NoTransactions",
        getMonthlySummary(2023, 7, testTransactions),
        MonthlySummaryResult.NoTransactions
    )

    checkMonthlySummary(
        "when year is after now should return error",
        getMonthlySummary(LocalDate.now().year + 1, 6, testTransactions),
        MonthlySummaryResult.Error("Cannot view summary for future years")
    )

    checkMonthlySummary(
        "when month is after current month in current year should return error",
        getMonthlySummary(LocalDate.now().year, LocalDate.now().monthValue + 1, testTransactions),
        MonthlySummaryResult.Error("Cannot view summary for future months")
    )

    checkMonthlySummary(
        "when month number is invalid should return error",
        getMonthlySummary(2023, 13, testTransactions),
        MonthlySummaryResult.Error("Month must be between 1 and 12")
    )

    checkMonthlySummary(
        "when year number is invalid should return error",
        getMonthlySummary(-2023, 6, testTransactions),
        MonthlySummaryResult.Error("Year must be positive")
    )

    checkMonthlySummary(
        "when valid month with transactions should return correct summary",
        getMonthlySummary(2023, 6, testTransactions),
        MonthlySummaryResult.Success(listOf(
            Transaction(1, 5000.0, "Salary", LocalDate.of(2023,6,1), salaryCategory, TransactionType.INCOME),
            Transaction(2, 200.0, "fuel", LocalDate.of(2023,6,5), carCategory, TransactionType.EXPENSE)
        ))
    )




//endregion
}

fun check(testName: String, result: Boolean, acceptedResult: Boolean){
    if (result == acceptedResult){
        println("Success - $testName")
    } else{
        println("Failed - $testName")
    }
}




//temp class for testing
sealed class MonthlySummaryResult {
    data class Error(val errorMessage: String) : MonthlySummaryResult()
    object NoTransactions : MonthlySummaryResult()
    data class Success(val transactions: List<Transaction>) : MonthlySummaryResult()
}