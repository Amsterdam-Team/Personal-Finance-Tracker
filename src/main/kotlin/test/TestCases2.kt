package test

import managers.CategoryManager
import managers.TransactionManager
import models.Category
import models.Transaction
import models.TransactionType
import saver.FileManagerImpl
import utils.ResultStatus
import utils.Validator
import java.time.LocalDate
import utils.Validator.isValidDate
import utils.Validator.isValidInput
import utils.Validator.isValidInputAmount
import utils.Validator.isValidTransactionType
import java.util.*

fun main(){

    val fileManager = FileManagerImpl()
    val transactionManager = TransactionManager(fileManager)
    val categoryManager = CategoryManager(fileManager)


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
    //endregion


//region Edit Transaction Test Cases


    check(
        testName = "when amount of existing transaction equals zero or negative number return false",
        result = transactionManager.editTransaction(Transaction(UUID.randomUUID(),0.0,"Shopping", LocalDate.now(),Category(
            UUID.randomUUID(),""),TransactionType.EXPENSE)),
        acceptedResult = ResultStatus.Error("Invalid Data")
    )
    check(
        testName = "when amount is something else number should return false",
        result = isValidInputAmount("fekdf"),
        acceptedResult = ResultStatus.Error("amount number shouldn't contain any letters or special characters")
    )
    check(
        testName = "when id of transaction is invalid should return false",
        result = transactionManager.editTransaction(Transaction(UUID.randomUUID(),0.0,"Shopping", LocalDate.now(),Category(
            UUID.randomUUID(),""),TransactionType.EXPENSE)),
        acceptedResult = ResultStatus.Error("Invalid Data")
    )
    check(
        testName = "when description is invalid like (numbers,special characters) should return false",
        result = transactionManager.editTransaction(Transaction(UUID.randomUUID(),0.0,"Shopping", LocalDate.now(),Category(
            UUID.randomUUID(),"$%#&$"),TransactionType.EXPENSE)),
        acceptedResult = ResultStatus.Error("Invalid Data")
    )

    check(
        testName = "when transaction type is empty should return false",
        result = isValidTransactionType(""),
        acceptedResult = ResultStatus.Error("please enter a transaction type ")
    )
    check(
        testName = "when category type is empty should return false",
        result = transactionManager.editTransaction(Transaction(UUID.randomUUID(),0.0," ", LocalDate.now(),Category(
            UUID.randomUUID(),""),TransactionType.EXPENSE)),
        acceptedResult = ResultStatus.Error("Invalid Data")
    )
    check(
        testName = "when category type is invalid return false",
        result =transactionManager.editTransaction(
            Transaction(UUID.randomUUID(),0.0,"Rent", LocalDate.now(), Category(
            UUID.randomUUID(),""), TransactionType.EXPENSE)
        ),
        acceptedResult = ResultStatus.Error("Invalid Data")
    )
    //endregion

    // region validators test cases
    check(
        testName = "when amount of existing transaction equals zero or negative number return false",
        result = isValidInputAmount("0.0"),
        acceptedResult = ResultStatus.Error("amount number should be more than zero")
    )
    check(
        testName = "when amount is something else number should return false",
        result = isValidInputAmount("iuhiuhis"),
        acceptedResult = ResultStatus.Error("amount number shouldn't contain any letters or special characters")
    )

    check(
        testName = "when description is invalid like (numbers,special characters) should return false",
        result = isValidInput("$#%@"),
        acceptedResult = ResultStatus.Error("input shouldn't be numbers or special characters only it must have a letters")
    )
    check(
        testName = "when description is empty should return false",
        result = isValidInput(""),
        acceptedResult = ResultStatus.Empty("please enter an input")
    )
    check(
        testName = "when date is invalid should return false",
        result = isValidDate("145224"),
        acceptedResult = ResultStatus.Error("please enter a valid date with that format : yyyy-MM-dd")
    )

    check(
        testName = "when transaction type is empty should return false",
        result = isValidInput(""),
        acceptedResult = ResultStatus.Empty("please enter an input")
    )
    check(
        testName = "when category type is empty should return ",
        result = isValidInput(""),
        acceptedResult = ResultStatus.Empty("please enter an input")
    )
    check(
        testName = "when category type is invalid return false",
        result = isValidInput("%#&*"),
        acceptedResult = ResultStatus.Error("input shouldn't be numbers or special characters only it must have a letters")
    )
    //endregion


    //region Category Test Cases

    val categoryId = UUID.randomUUID()
    check(

        testName = "When the user tries to add a valid category  should return true ",
        result = categoryManager.addCategory(categoryId,"food"),
        acceptedResult =ResultStatus.Success("Category Added Successfully!") ,
    )
    check(

        testName = "When the user tries to add a repeated category  should return false ",
        result = categoryManager.addCategory(categoryId,"food"),
        acceptedResult =ResultStatus.Error("Invalid Data") ,
    )
    check(

        testName = "When the user tries to remove a valid category  should return true ",
        result = categoryManager.deleteCategoryById(categoryId.toString()),
        acceptedResult =ResultStatus.Success("Category deleted successfully!") ,
    )

    //endregion


}

fun <T> check(testName: String, result: T, acceptedResult: T) {
    if(result == acceptedResult) println("Success - $testName")
    else println("Fail - $testName")
}