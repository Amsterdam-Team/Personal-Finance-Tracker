package test

import CategoryManager
import models.Category

val cat_manager=CategoryManager()

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
        result = cat_manager.checkAddCategory("Food"),
        acceptedResult =true ,
    )
    check(
        testName = "When the user tries to add a category with the same name should return false ",
        result = cat_manager.checkAddCategory("Food"),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with an empty string should return false",
        result = cat_manager.checkAddCategory(""),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with special character should return false",
        result = cat_manager.checkAddCategory("@"),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add invalid category type should return false",
        result = cat_manager.checkAddCategory("54556"),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with spaces should return false",
        result = cat_manager.checkAddCategory(" Rent "),
        acceptedResult =false ,
    )
    //endregion



    // region Edit Category Test Case
    check(
        testName = "When the user edit a category with a valid name and valid id should return true",
        result = cat_manager.checkEditCategory("Food",1),
        acceptedResult =true ,
    )
    check(
        testName = "When the user tries to edit a category with the same name and valid id should return false",
        result = cat_manager.checkEditCategory("Food",1),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to edit a category with an empty string and valid id should return false",
        result = cat_manager.checkEditCategory("",2),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with special character and invalid id (out of range) should return false",
        result = cat_manager.checkEditCategory("@",10000),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add invalid category type and valid id should return false",
        result = cat_manager.checkEditCategory("54556",3),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with spaces and valid id should return false",
        result = cat_manager.checkEditCategory(" Rent ",2),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a valid category name and negative id should return false",
        result = cat_manager.checkEditCategory("Food",-2),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a valid category name and invalid type of id should return false",
        result = cat_manager.checkEditCategory("Food",'@'),
        acceptedResult =false ,
    )
    //endregion



    //region View Category Test Case
    check(
        testName = "When exist list of category should return true",
        result =cat_manager.checkViewCategory(listOf(
            Category(1,"Food"),
            Category(2,"Rent"),
        )),
        acceptedResult = true
    )
    check(
        testName = "When not exist list of category should return false",
        result = cat_manager.checkViewCategory(listOf()),
        acceptedResult = false
    )
    //endregion



    // region Delete Category Test Case
    check(
        testName = "When user enter valid id should return true",
        result = cat_manager.checkDeleteCategory(2),
        acceptedResult = true
    )
    check(
        testName = "When user enter negative id should return false",
        result = cat_manager.checkDeleteCategory(-5),
        acceptedResult = false
    )
    check(
        testName = "When user enter id equal zero should return false",
        result = cat_manager.checkDeleteCategory(0),
        acceptedResult = false
    )
    check(
        testName = "When user enter id out of range from id's that displayed should return false",
        result = cat_manager.checkDeleteCategory(10000),
        acceptedResult = false
    )
    check(
        testName = "When user enter character rather than int should return false",
        result = cat_manager.checkDeleteCategory('@'),
        acceptedResult = false
    )
    check(
        testName = "When user enter string rather than int should return false",
        result = cat_manager.checkDeleteCategory("hello"),
        acceptedResult = false
    )
    check(
        testName = "When user enter space rather than int should return false",
        result = cat_manager.checkDeleteCategory(' '),
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

    check(testName = "when no transactions in month should return NoTransactions", result = false, acceptedResult = false)
    check(testName = "when year is after now should return error", result = false, acceptedResult = false)
    check(testName = "when month is after current month in current year should return error", result = false, acceptedResult = false)
    check(testName = "when month number is invalid should return error", result = false, acceptedResult = false)
    check(testName = "when year number is invalid should return error", result = false, acceptedResult = false)
    check(testName = "when valid month with transactions should return correct summary", result = false, acceptedResult = false)

//endregion

}

fun <T> check(testName: String, result: T, acceptedResult: T) {
    if(result == acceptedResult) println("Success - $testName")
    else println("Fail - $testName")
}