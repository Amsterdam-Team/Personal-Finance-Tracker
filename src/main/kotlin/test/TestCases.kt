package test

import Category
import checkAddCategory
import checkDeleteCategory
import checkEditCategory
import checkViewCategory


fun main(){
//region Transactions Test Cases
//todo: write all test cases that related with transactions here :)

//endregion

//region Category Test Cases

    //add Category Test Case
    check(
        testName = "When the user adds a category with a valid name return true",
        result = checkAddCategory("Food"),
        acceptedResult =true ,
    )
    check(
        testName = "When the user tries to add a category with the same name",
        result = checkAddCategory("Food"),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with an empty string",
        result = checkAddCategory(""),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with special character",
        result = checkAddCategory("@"),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add invalid category type",
        result = checkAddCategory("54556"),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with spaces",
        result = checkAddCategory(" Rent "),
        acceptedResult =false ,
    )


    //Edit Category Test Case
    check(
        testName = "When the user edit a category with a valid name and valid id return true",
        result = checkEditCategory("Food",1),
        acceptedResult =true ,
    )
    check(
        testName = "When the user tries to edit a category with the same name and valid id return false",
        result = checkEditCategory("Food",1),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to edit a category with an empty string and valid id return false",
        result = checkEditCategory("",2),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with special character and invalid id (out of range) return false",
        result = checkEditCategory("@",10000),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add invalid category type and valid id return false",
        result = checkEditCategory("54556",3),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a category with spaces and valid id return false",
        result = checkEditCategory(" Rent ",2),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a valid category name and negative id return false",
        result = checkEditCategory("Food",-2),
        acceptedResult =false ,
    )
    check(
        testName = "When the user tries to add a valid category name and invalid type of id return false",
        result = checkEditCategory("Food",'@'),
        acceptedResult =false ,
    )


    //View Category Test Case
    check(
        testName = "When exist list of category return true",
        result =checkViewCategory(listOf(
            Category("Food",1),
            Category("Rent",2),
        )),
        acceptedResult = true
    )
    check(
        testName = "When not exist list of category return false",
        result =checkViewCategory(listOf()),
        acceptedResult = false
    )


    //Delete Category Test Case
    check(
        testName = "When user enter valid id return true",
        result = checkDeleteCategory(2),
        acceptedResult = true
    )
    check(
        testName = "When user enter negative id return false",
        result = checkDeleteCategory(-5),
        acceptedResult = false
    )
    check(
        testName = "When user enter id equal zero return false",
        result = checkDeleteCategory(0),
        acceptedResult = false
    )
    check(
        testName = "When user enter id out of range from id's that displayed return false",
        result = checkDeleteCategory(10000),
        acceptedResult = false
    )
    check(
        testName = "When user enter character rather than int return false",
        result = checkDeleteCategory('@'),
        acceptedResult = false
    )
    check(
        testName = "When user enter string rather than int return false",
        result = checkDeleteCategory("hello"),
        acceptedResult = false
    )
    check(
        testName = "When user enter space rather than int return false",
        result = checkDeleteCategory(' '),
        acceptedResult = false
    )

//endregion

//region Report Test Cases
//todo: write all test cases that related with Report here :)

//endregion
}

fun check(testName: String, result: Boolean, acceptedResult: Boolean){
    if (result == acceptedResult){
        println("Success - $testName")
    } else{
        println("Failed - $testName")
    }
}