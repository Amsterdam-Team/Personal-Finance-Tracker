package test

import Validators.isValidCategoryName
import Validators.isValidDescription
import models.Category
import utils.ResultStatus
import managers.*
import models.Transaction
import models.TransactionType
import models.reports.CategorySummary
import models.reports.MonthlySummary
import reports.MonthlySummaryManager
import java.time.LocalDate
import java.util.*
import saver.FileManagerImpl

import utils.Validator.isValidInputAmount
import utils.Validator.isValidTransactionType
import saver.IFileManager
import utils.Validator.isValidDate


fun main() {
//region Transactions Test Cases
    val transactionManager = TransactionManager(fileManager = FileManagerImpl())
//region Edit Transaction Test Cases


    check(
        testName = "when amount of existing transaction equals zero or negative number return false",
        result = transactionManager.editTransaction(
            Transaction(
                UUID.randomUUID(), 0.0, "Shopping", LocalDate.now(), Category(
                    UUID.randomUUID(), ""
                ), TransactionType.EXPENSE
            )
        ),
        acceptedResult = ResultStatus.Error("Invalid Amount")
    )
    check(
        testName = "when amount is something else number should return false",
        result = isValidInputAmount(" "),
        acceptedResult = ResultStatus.Error("Invalid Amount")
    )
    check(
        testName = "when id of transaction is invalid should return false",
        result = transactionManager.editTransaction(
            Transaction(
                UUID.randomUUID(), 0.0, "Shopping", LocalDate.now(), Category(
                    UUID.randomUUID(), ""
                ), TransactionType.EXPENSE
            )
        ),
        acceptedResult = ResultStatus.Error("Invalid Id")
    )
    check(
        testName = "when description is invalid like (numbers,special characters) should return false",
        result = transactionManager.editTransaction(
            Transaction(
                UUID.randomUUID(), 0.0, "Shopping", LocalDate.now(), Category(
                    UUID.randomUUID(), "$%#&$"
                ), TransactionType.EXPENSE
            )
        ),
        acceptedResult = ResultStatus.Error("Invalid Description")
    )
    check(
        testName = "when description is empty should return true",
        result = transactionManager.editTransaction(
            Transaction(
                UUID.randomUUID(), 0.0, "Shopping", LocalDate.now(), Category(
                    UUID.randomUUID(), ""
                ), TransactionType.EXPENSE
            )
        ),
        acceptedResult = ResultStatus.Success("success")
    )
    check(
        testName = "when date is invalid should return false",
        result = isValidDate("145224"),
        acceptedResult = ResultStatus.Error("Invalid Date")
    )

    check(
        testName = "when transaction type is empty should return false",
        result = isValidTransactionType(""),
        acceptedResult = ResultStatus.Error("Invalid Type")
    )
    check(
        testName = "when category type is empty should return false",
        result = transactionManager.editTransaction(
            Transaction(
                UUID.randomUUID(), 0.0, " ", LocalDate.now(), Category(
                    UUID.randomUUID(), ""
                ), TransactionType.EXPENSE
            )
        ),
        acceptedResult = ResultStatus.Error("Invalid Category")
    )
    check(
        testName = "when category type is invalid return false",
        result = transactionManager.editTransaction(
            Transaction(
                UUID.randomUUID(), 0.0, "Rent", LocalDate.now(), Category(
                    UUID.randomUUID(), ""
                ), TransactionType.EXPENSE
            )
        ),
        acceptedResult = ResultStatus.Error("Invalid Category")
    )
    //endregion
//endregion



}

fun <T> check(testName: String, result: T, acceptedResult: T) {
    if (result == acceptedResult) println("Success - $testName")
    else println("Fail - $testName")
}
