package test

import Category
import category_manager

val cat_manager=category_manager()

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

    // region add Category Test Case
    check(
        testName = "When the user adds a category with a valid name should return true",
        result = test.cat_manager.checkAddCategory("Food"),
        acceptedResult =true ,
    )
    check(
        testName = "When the user tries to add a category with the same name should return false ",
        result = test.cat_manager.checkAddCategory("Food"),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with an empty string should return false",
        result = test.cat_manager.checkAddCategory(""),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with special character should return false",
        result = test.cat_manager.checkAddCategory("@"),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add invalid category type should return false",
        result = test.cat_manager.checkAddCategory("54556"),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with spaces should return false",
        result = test.cat_manager.checkAddCategory(" Rent "),
        acceptedResult =false ,
    )
    //endregion



    // region Edit Category Test Case
    check(
        testName = "When the user edit a category with a valid name and valid id should return true",
        result = test.cat_manager.checkEditCategory("Food",1),
        acceptedResult =true ,
    )
    check(
        testName = "When the user tries to edit a category with the same name and valid id should return false",
        result = test.cat_manager.checkEditCategory("Food",1),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to edit a category with an empty string and valid id should return false",
        result = test.cat_manager.checkEditCategory("",2),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with special character and invalid id (out of range) should return false",
        result = test.cat_manager.checkEditCategory("@",10000),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add invalid category type and valid id should return false",
        result = test.cat_manager.checkEditCategory("54556",3),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with spaces and valid id should return false",
        result = test.cat_manager.checkEditCategory(" Rent ",2),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a valid category name and negative id should return false",
        result = test.cat_manager.checkEditCategory("Food",-2),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a valid category name and invalid type of id should return false",
        result = test.cat_manager.checkEditCategory("Food",'@'),
        acceptedResult =false ,
    )
    //endregion



    //region View Category Test Case
    check(
        testName = "When exist list of category should return true",
        result =test.cat_manager.checkViewCategory(listOf(
            Category("Food",1),
            Category("Rent",2),
        )),
        acceptedResult = true
    )
    check(
        testName = "When not exist list of category should return false",
        result =test.cat_manager.checkViewCategory(listOf()),
        acceptedResult = false
    )
    //endregion



    // region Delete Category Test Case
    check(
        testName = "When user enter valid id should return true",
        result = test.cat_manager.checkDeleteCategory(2),
        acceptedResult = true
    )
    check(
        testName = "When user enter negative id should return false",
        result = test.cat_manager.checkDeleteCategory(-5),
        acceptedResult = false
    )
    check(
        testName = "When user enter id equal zero should return false",
        result = test.cat_manager.checkDeleteCategory(0),
        acceptedResult = false
    )
    check(
        testName = "When user enter id out of range from id's that displayed should return false",
        result = test.cat_manager.checkDeleteCategory(10000),
        acceptedResult = false
    )
    check(
        testName = "When user enter character rather than int should return false",
        result = test.cat_manager.checkDeleteCategory('@'),
        acceptedResult = false
    )
    check(
        testName = "When user enter string rather than int should return false",
        result = test.cat_manager.checkDeleteCategory("hello"),
        acceptedResult = false
    )
    check(
        testName = "When user enter space rather than int should return false",
        result = test.cat_manager.checkDeleteCategory(' '),
        acceptedResult = false
    )
    //endregion


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

fun check(testName: String, result: Boolean, acceptedResult: Boolean){
    if (result == acceptedResult){
        println("Success - $testName")
    } else{
        println("Failed - $testName")
    }
}