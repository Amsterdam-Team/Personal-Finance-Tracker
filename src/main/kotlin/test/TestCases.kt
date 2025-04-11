package test
import models.Category
import models.Transaction
import models.TransactionType
import models.reports.CategorySummary
import models.reports.MonthlySummary
import reports.MonthlySummaryManager
import utils.ResultStatus
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
//region Edit Transaction Test Cases

    check(
        testName = "When all inputs are valid and transaction exists, then return true",
        result = true,
        acceptedResult = true
    )

    check(
        testName = "when amount of existing transaction equals zero or negative number return false",
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

    // region add Category Test Case
    check(
        testName = "When the user adds a category with a valid name should return true",
        result = false,
        acceptedResult =true ,
    )
    check(
        testName = "When the user tries to add a category with the same name should return false ",
        result = false,
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with an empty string should return false",
        result = false,
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with special character should return false",
        result = false,
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add invalid category type should return false",
        result = false,
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with spaces should return false",
        result = false,
        acceptedResult =false ,
    )
    //endregion



    // region Edit Category Test Case
    check(
        testName = "When the user edit a category with a valid name and valid id should return true",
        result = false,
        acceptedResult =true ,
    )
    check(
        testName = "When the user tries to edit a category with the same name and valid id should return false",
        result = false,
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to edit a category with an empty string and valid id should return false",
        result = false,
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with special character and invalid id (out of range) should return false",
        result = false,
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add invalid category type and valid id should return false",
        result = false,
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with spaces and valid id should return false",
        result = false,
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a valid category name and negative id should return false",
        result = false,
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a valid category name and invalid type of id should return false",
        result = false,
        acceptedResult =false ,
    )
    //endregion



    //region View Category Test Case
    check(
        testName = "When exist list of category should return true",
        result = false,
        acceptedResult = true
    )
    check(
        testName = "When not exist list of category should return false",
        result = false,
        acceptedResult = false
    )
    //endregion



    // region Delete Category Test Case
    check(
        testName = "When user enter valid id should return true",
        result = false,
        acceptedResult = true
    )
    check(
        testName = "When user enter negative id should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "When user enter id equal zero should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "When user enter id out of range from id's that displayed should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "When user enter character rather than int should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "When user enter string rather than int should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "When user enter space rather than int should return false",
        result = false,
        acceptedResult = false
    )
    //endregion


//endregion

//region view balance report test cases
    check(
        testName = "1. When there are no transactions in the date range, should return zero income, zero expenses, and zero net balance",
        result = "",
        acceptedResult = ""
    )

    check(
        testName = "2. When only income transactions exist in the date range, should return correct income total and zero expenses",
        result = "",
        acceptedResult = ""
    )

    check(
        testName = "3. When only expense transactions exist in the date range, should return correct expense total and zero income",
        result = "",
        acceptedResult = ""
    )

    check(
        testName = "4. When both income and expense transactions exist, should return correct totals and net balance",
        result = "",
        acceptedResult = ""
    )

    check(
        testName = "5. When start date is after end date, should return null",
        result = null,
        acceptedResult = ""
    )

    check(
        testName = "6. When start or end date is invalid should return null",
        result = null,
        acceptedResult = ""
    )

    check(
        testName = "7. When transactions fall exactly on the start or end date, should include them in the result",
        result = "",
        acceptedResult = ""
    )

    check(
        testName = "8. When transactions exist but are completely outside the date range, should be excluded from the result",
        result = "",
        acceptedResult = ""
    )

    check(
        testName = "9. When all transactions in the range have zero amounts, should return zero income, zero expense, and zero net balance",
        result = "",
        acceptedResult = ""
    )
//endregion


//region Monthly Summary Test Cases



    val carCategory = Category(id = 1, name = "car")
    val salaryCategory = Category(id = 2, name = "salary")
    val rentCategory = Category(id = 3, name = "rent")

    val testTransactions = listOf(
        Transaction(1, 5000.0, "Salary", LocalDate.of(2023,6,1), salaryCategory, TransactionType.INCOME),
        Transaction(2, 200.0, "fuel", LocalDate.of(2023,6,5), carCategory, TransactionType.EXPENSE),
        Transaction(3, 1000.0, "Rent", LocalDate.of(2023,5,1), rentCategory, TransactionType.EXPENSE)
    )

    check(
        "when no transactions in month should return Empty",
        MonthlySummaryManager().getMonthlySummary(2023, 7, testTransactions),
        ResultStatus.Empty("There are no transactions this month")
    )

    check(
        "when year is after now should return Error",
        MonthlySummaryManager().getMonthlySummary(LocalDate.now().year + 1, 6, testTransactions),
        ResultStatus.Error("Cannot view summary for future years")
    )

    check(
        "when month is after current month in current year should return Error",
        MonthlySummaryManager().getMonthlySummary(LocalDate.now().year, LocalDate.now().monthValue + 1, testTransactions),
        ResultStatus.Error("Cannot view summary for future months")
    )

    check(
        "when month number is invalid should return Error",
        MonthlySummaryManager().getMonthlySummary(2023, 13, testTransactions),
        ResultStatus.Error("Month must be between 1 and 12")
    )

    check(
        "when year number is invalid should return Error",
        MonthlySummaryManager().getMonthlySummary(1999, 6, testTransactions),
        ResultStatus.Error("Year must be 2000 or later")
    )

    check(
        "when valid month with transactions should return correct summary",
        MonthlySummaryManager().getMonthlySummary(2023, 6, testTransactions),
        ResultStatus.Success(
            MonthlySummary(
                categorySummaries = listOf(
                    CategorySummary(
                        category = salaryCategory,
                        type = TransactionType.INCOME,
                        totalAmount = 5000.0
                    ),
                    CategorySummary(
                        category = carCategory,
                        type = TransactionType.EXPENSE,
                        totalAmount = 200.0
                    )
                ),
                transactions = listOf(
                    Transaction(1, 5000.0, "Salary", LocalDate.of(2023,6,1), salaryCategory, TransactionType.INCOME),
                    Transaction(2, 200.0, "fuel", LocalDate.of(2023,6,5), carCategory, TransactionType.EXPENSE)
                )
            )
        )
    )

//endregion

}

fun <T> check(testName: String, result: T, acceptedResult: T) {
    if(result == acceptedResult) println("Success - $testName")
    else println("Fail - $testName")
}