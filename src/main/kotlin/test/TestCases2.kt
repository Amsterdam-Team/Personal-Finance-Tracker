package test

import managers.CategoryManager
import managers.TransactionManager
import saver.FileManagerImpl
import utils.ResultStatus
import utils.Validator
import java.util.*

fun main(){

    val fileManager = FileManagerImpl()
    val transactionManager = TransactionManager(fileManager)
    val categoryManager = CategoryManager(fileManager)



    // region validators test cases
    check(
        testName = "when amount of existing transaction equals zero or negative number return false",
        result = Validator.isValidInputAmount("0.0"),
        acceptedResult = ResultStatus.Error("amount number should be more than zero")
    )
    check(
        testName = "when amount is something else number should return false",
        result = Validator.isValidInputAmount("iuhiuhis"),
        acceptedResult = ResultStatus.Error("amount number shouldn't contain any letters or special characters")
    )

    check(
        testName = "when description is invalid like (numbers,special characters) should return false",
        result = Validator.isValidInput("$#%@"),
        acceptedResult = ResultStatus.Error("input shouldn't be numbers or special characters only it must have a letters")
    )
    check(
        testName = "when description is empty should return false",
        result = Validator.isValidInput(""),
        acceptedResult = ResultStatus.Empty("please enter an input")
    )
    check(
        testName = "when date is invalid should return false",
        result = Validator.isValidDate("145224"),
        acceptedResult = ResultStatus.Error("please enter a valid date with that format : yyyy-MM-dd")
    )

    check(
        testName = "when transaction type is empty should return false",
        result = Validator.isValidInput(""),
        acceptedResult = ResultStatus.Empty("please enter an input")
    )
    check(
        testName = "when category type is empty should return ",
        result = Validator.isValidInput(""),
        acceptedResult = ResultStatus.Empty("please enter an input")
    )
    check(
        testName = "when category type is invalid return false",
        result = Validator.isValidInput("%#&*"),
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


    //region add Category Test Case
    check(

        testName = "When the user tries to add a category with the same name should return false ",
        result = categoryManager.addCategory(UUID.randomUUID(),"Salary"),
        acceptedResult = ResultStatus.Error("Invalid Data") ,
    )
    check(
        testName = "When the user tries to add a category with an empty string should return false",
        result = categoryManager.addCategory(UUID.randomUUID(),""),
        acceptedResult =ResultStatus.Error("Invalid Data") ,
    )
    check(
        testName = "When the user tries to add a category with special character should return false",
        result = categoryManager.addCategory(UUID.randomUUID(),"$#%#"),
        acceptedResult =ResultStatus.Error("Invalid Data") ,
    )
    check(
        testName = "When the user tries to add invalid category type should return false",
        result = categoryManager.addCategory(UUID.randomUUID(),"123"),
        acceptedResult =ResultStatus.Error("Invalid Data") ,
    )
    check(
        testName = "When the user tries to add a category with spaces should return false",
        result = categoryManager.addCategory(UUID.randomUUID(),"Salary "),
        acceptedResult = ResultStatus.Error("Invalid Data") ,
    )

    //endregion

    //endregion


}

fun <T> check(testName: String, result: T, acceptedResult: T) {
    if(result == acceptedResult) println("Success - $testName")
    else println("Fail - $testName")
}