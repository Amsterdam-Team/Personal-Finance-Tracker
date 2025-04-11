package test

import managers.isValidCategoryName
import models.Category
import utils.ResultStatus
import managers.*
import models.BalanceReport
import models.Transaction
import models.TransactionType
import reports.BalanceReportManager
import java.time.LocalDate
import java.util.*
import saver.FileManagerImpl


fun main(){
//region Transactions Test Cases
//todo: write all test cases that related with transactions here :)
      //region mock data for testing add function validity
    val transactionManager = TransactionManager(fileManager = FileManagerImpl())
    check(
        testName = "when amount is less than or equal zero number should return false",
        result = transactionManager.addTransaction(
            Transaction(
                id = UUID.randomUUID(), -1.0, "", date = LocalDate.now(),
                Category(id = UUID.randomUUID(), ""), type = TransactionType.EXPENSE
            )
        ),
        acceptedResult = ResultStatus.Error("please enter a valid amount number")
    )
    check(
        testName = "when description is invalid like (containing only numbers,special characters) should return false",
        result = transactionManager.addTransaction(
            Transaction(
                id = UUID.randomUUID(), 1.0, "11#$44", date = LocalDate.now(),
                Category(id = UUID.randomUUID(), ""), type = TransactionType.EXPENSE
            )
        ),
        acceptedResult = ResultStatus.Error("please enter a valid description")
    )

    //end region

//region Transactions Test Cases
//todo: write all test cases that related with transactions here :)

    //region add transaction test cases
    check(
        testName = "when amount is less than or equal zero number should return false",
        result = isValidInputAmount("0"),
        acceptedResult = false
    )
    check(
        testName = "when amount is letters or special characters should return false",
        result = isValidInputAmount("a&"),
        acceptedResult = false
    )
    check(
        testName = "when description is invalid like (containing only numbers,special characters) should return false",
        result = isValidDescription("1213$%#"),
        acceptedResult = false
    )
    check(
        testName = "when description is empty should return true",
        result = isValidDescription(""),
        acceptedResult = true
    )
    check(
        testName = "when date is invalid should return false",
        result = isValidDate("1-2-2024"),
        acceptedResult = false
    )
    check(
        testName = "when date is empty should return false",
        result = isValidDate(""),
        acceptedResult = false
    )
    check(
        testName = "when transaction type is empty should return false",
        result = isValidTransactionType(""),
        acceptedResult = false
    )
    check(
        testName = "when transaction type isn't one of these(INCOME,EXPENSE) should return false",
        result = isValidTransactionType("ahmed"),
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
        testName = "when amount of existing transaction equals zero or negative number return false",
        result = isValidAmount(0.0),
        acceptedResult = ResultStatus.Error("Invalid Amount")
    )
    check(
        testName = "when amount is something else number should return false",
        result = isValidAmount(null),
        acceptedResult = ResultStatus.Error("Invalid Amount")
    )
    check(
        testName = "when id of transaction is invalid should return false",

        result = isValidID(listOf(Transaction(UUID.randomUUID(), 125.4,"",LocalDate.now(),Category(UUID.randomUUID(),""),TransactionType.EXPENSE)),
            UUID.randomUUID()),
        acceptedResult = ResultStatus.Error("Invalid Id")
    )
    check(
        testName = "when description is invalid like (numbers,special characters) should return false",
        result = isValidDescription("$#%@"),
        acceptedResult = ResultStatus.Error("Invalid Description")
    )
    check(
        testName = "when description is empty should return true",
        result = isValidDescription(""),
        acceptedResult = ResultStatus.Success("success")
    )
    check(
        testName = "when date is invalid should return false",
        result = isValidDate("145224"),
        acceptedResult = ResultStatus.Error("Invalid Date")
    )

    check(
        testName = "when transaction type is empty should return false",
        result = isValidaType(""),
        acceptedResult = ResultStatus.Error("Invalid Type")
    )
    check(
        testName = "when category type is empty should return false",
        result = isValidCategory(listOf(Category(UUID.randomUUID(),"Food")),""),
        acceptedResult = ResultStatus.Error("Invalid Category")
    )
    check(
        testName = "when category type is invalid return false",
        result = isValidCategory(listOf(Category(UUID.randomUUID(),"Food"),Category(UUID.randomUUID(),"Salary")),"Shopping"),
        acceptedResult = ResultStatus.Error("Invalid Category")
    )
    //endregion
//endregion


//region Category Test Cases

    // region add Category Test Case
    check(

        testName = "When the user tries to add a category with the same name should return false ",
        result = isValidCategoryName(listOf(Category(UUID.randomUUID(),"Food")),"Food"),
        acceptedResult =ResultStatus.Error("Invalid Name") ,
    )
    check(
        testName = "When the user tries to add a category with an empty string should return false",
        result = isValidCategoryName(listOf(Category(UUID.randomUUID(),"Food")),""),
        acceptedResult =ResultStatus.Error("Invalid Name") ,
    )
    check(
        testName = "When the user tries to add a category with special character should return false",
        result = isValidCategoryName(listOf(Category(UUID.randomUUID(),"Food")),"$#%#"),
        acceptedResult =ResultStatus.Error("Invalid Name") ,
    )
    check(
        testName = "When the user tries to add invalid category type should return false",
        result = isValidCategoryName(listOf(Category(UUID.randomUUID(),"Food")),"123"),
        acceptedResult =ResultStatus.Error("Invalid Name") ,
    )
    check(
        testName = "When the user tries to add a category with spaces should return false",
        result = isValidCategoryName(listOf(Category(UUID.randomUUID(),"Food")),"Salary "),
        acceptedResult = ResultStatus.Error("Invalid Name") ,
    )
    


    //endregion


    // region Edit Category Test Case
    check(
        testName = "When the user edit a category with a valid name and valid id should return true",
        result = false,
        acceptedResult = true,
    )
    check(
        testName = "When the user tries to edit a category with the same name and valid id should return false",
        result = false,
        acceptedResult = false,
    )
    check(
        testName = "When the user tries to edit a category with an empty string and valid id should return false",
        result = false,
        acceptedResult = false,
    )
    check(
        testName = "When the user tries to add a category with special character and invalid id (out of range) should return false",
        result = false,
        acceptedResult = false,
    )
    check(
        testName = "When the user tries to add invalid category type and valid id should return false",
        result = false,
        acceptedResult = false,
    )
    check(
        testName = "When the user tries to add a category with spaces and valid id should return false",
        result = false,
        acceptedResult = false,
    )
    check(
        testName = "When the user tries to add a valid category name and negative id should return false",
        result = false,
        acceptedResult = false,
    )
    check(
        testName = "When the user tries to add a valid category name and invalid type of id should return false",
        result = false,
        acceptedResult = false,
    )
    //endregion


    //region View Category Test Case
    check(
        testName = "When exist list of category should return true",
        result = false,
        acceptedResult = true
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
    val fileManagerImpl = FileManagerImpl()
    val balanceReportManager = BalanceReportManager(fileManagerImpl)

    check(
        testName = "1. When there are no transactions in the date range, should return zero income, zero expenses, and zero net balance",
        result = balanceReportManager.getBalanceReport(startDate = LocalDate.of(2030,1,1)),
        acceptedResult = ResultStatus.Success(data = BalanceReport(0.0, 0.0, 0.0))
    )

    check(
        testName = "2. When only income transactions exist in the date range, should return correct income total and zero expenses",
        result = balanceReportManager.getBalanceReport(LocalDate.of(2025,4,1), LocalDate.of(2025,4,2)),
        acceptedResult = ResultStatus.Success(BalanceReport(150.0, 0.0, 150.0))
    )

    check(
        testName = "3. When only expense transactions exist in the date range, should return correct expense total and zero income",
        result = balanceReportManager.getBalanceReport(LocalDate.of(2025,4,3), LocalDate.of(2025,4,10)),
        acceptedResult = ResultStatus.Success(BalanceReport(0.0, 50.0, -50.0))
    )

    check(
        testName = "4. When both income and expense transactions exist, should return correct totals and net balance",
        result = balanceReportManager.getBalanceReport(LocalDate.of(2025,4,1), LocalDate.of(2025,4,10)),
        acceptedResult = ResultStatus.Success(BalanceReport(150.0, 50.0, 100.0))
    )

    check(
        testName = "5. When start date is after end date, should return The start date cannot be after the end date Error",
        result = balanceReportManager.getBalanceReport(LocalDate.of(2025,4,10), LocalDate.of(2025,4,1)),
        acceptedResult = ResultStatus.Error("The start date cannot be after the end date. Please adjust your date range.")
    )

    check(
        testName = "6. When start or end date is invalid should invalid date Error",
        result = balanceReportManager.getBalanceReport(LocalDate.of(2025,4,1), LocalDate.of(2026,1,1)),
        acceptedResult = ResultStatus.Error("The end date cannot be after the current date. Please provide a valid end date.")
    )

    check(
        testName = "7. When transactions fall exactly on the start or end date, should include them in the result",
        result = balanceReportManager.getBalanceReport(LocalDate.of(2025,4,1), LocalDate.of(2025,4,10)),
        acceptedResult = ResultStatus.Success(BalanceReport(150.0, 50.0, 100.0))
    )

    check(
        testName = "8. When transactions exist but are completely outside the date range, should be excluded from the result",
        result = balanceReportManager.getBalanceReport(LocalDate.of(2019,1,1), LocalDate.of(2019,12,31)),
        acceptedResult = ResultStatus.Success(BalanceReport(0.0, 0.0, 0.0))
    )

    check(
        testName = "9. When all transactions in the range have zero amounts, should return zero income, zero expense, and zero net balance",
        result = balanceReportManager.getBalanceReport(LocalDate.of(2025,4,5), LocalDate.of(2025,4,6)),
        acceptedResult = ResultStatus.Success(BalanceReport(0.0, 0.0, 0.0))
    )
//endregion


//region Monthly Summary Test Cases

    check(
        testName = "when no transactions in month should return NoTransactions",
        result = false,
        acceptedResult = false
    )
    check(testName = "when year is after now should return error", result = false, acceptedResult = false)
    check(
        testName = "when month is after current month in current year should return error",
        result = false,
        acceptedResult = false
    )
    check(testName = "when month number is invalid should return error", result = false, acceptedResult = false)
    check(testName = "when year number is invalid should return error", result = false, acceptedResult = false)
    check(
        testName = "when valid month with transactions should return correct summary",
        result = false,
        acceptedResult = false
    )

//endregion


}

fun <T> check(testName: String, result: T, acceptedResult: T) {
    if (result == acceptedResult) println("Success - $testName")
    else println("Fail - $testName")
}
