////package test
//package test
//
////import managers.isValidCategoryName
////import models.Category
////import utils.ResultStatus
////import managers.*
////import models.Transaction
////import models.TransactionType
////import java.time.LocalDate
////import java.util.*
////import saver.FileManagerImpl
////
////
////fun main(){
//////region Transactions Test Cases
//////todo: write all test cases that related with transactions here :)
////      //region mock data for testing add function validity
////       // region view transaction test cases
////        check(
////            testName = "when transaction id is not found then should return null",
////            result= false,
////            acceptedResult = false,
////        )
////        check(
////            testName = "when transaction id is found then should return transaction",
////            result= true,
////            acceptedResult = true,
////        )
////        check(
////            testName = "when transaction id is not valid then should return null",
////            result= false,
////            acceptedResult = false,
////        )
////        // endregion
//
////    check(
////        testName = "When the user tries to add a valid category name and invalid type of id should return false",
////        result = false,
////        acceptedResult = false,
////    )
////    //endregion
////
////
////    //region View Category Test Case
////    check(
////        testName = "When exist list of category should return true",
////        result = false,
////        acceptedResult = true
////    )
////
////    //endregion
////
////
////    // region Delete Category Test Case
////    check(
////        testName = "When user enter valid id should return true",
////        result = false,
////        acceptedResult = true
////    )
////    check(
////        testName = "When user enter negative id should return false",
////        result = false,
////        acceptedResult = false
////    )
////    check(
////        testName = "When user enter id equal zero should return false",
////        result = false,
////        acceptedResult = false
////    )
////    check(
////        testName = "When user enter id out of range from id's that displayed should return false",
////        result = false,
////        acceptedResult = false
////    )
////    check(
////        testName = "When user enter character rather than int should return false",
////        result = false,
////        acceptedResult = false
////    )
////    check(
////        testName = "When user enter string rather than int should return false",
////        result = false,
////        acceptedResult = false
////    )
////    check(
////        testName = "When user enter space rather than int should return false",
////        result = false,
////        acceptedResult = false
////    )
////    //endregion
////
////
//////endregion
////
//////region view balance report test cases
////    check(
////        testName = "1. When there are no transactions in the date range, should return zero income, zero expenses, and zero net balance",
////        result = "",
////        acceptedResult = ""
////    )
////
////    check(
////        testName = "2. When only income transactions exist in the date range, should return correct income total and zero expenses",
////        result = "",
////        acceptedResult = ""
////    )
////
////    check(
////        testName = "3. When only expense transactions exist in the date range, should return correct expense total and zero income",
////        result = "",
////        acceptedResult = ""
////    )
////
////    check(
////        testName = "4. When both income and expense transactions exist, should return correct totals and net balance",
////        result = "",
////        acceptedResult = ""
////    )
////
////    check(
////        testName = "5. When start date is after end date, should return null",
////        result = null,
////        acceptedResult = ""
////    )
////
////    check(
////        testName = "6. When start or end date is invalid should return null",
////        result = null,
////        acceptedResult = ""
////    )
////
////    check(
////        testName = "7. When transactions fall exactly on the start or end date, should include them in the result",
////        result = "",
////        acceptedResult = ""
////    )
////
////    check(
////        testName = "8. When transactions exist but are completely outside the date range, should be excluded from the result",
////        result = "",
////        acceptedResult = ""
////    )
////
////    check(
////        testName = "9. When all transactions in the range have zero amounts, should return zero income, zero expense, and zero net balance",
////        result = "",
////        acceptedResult = ""
////    )
//////endregion
////
////
//////region Monthly Summary Test Cases
////
////    check(
////        testName = "when no transactions in month should return NoTransactions",
////        result = false,
////        acceptedResult = false
////    )
////    check(testName = "when year is after now should return error", result = false, acceptedResult = false)
////    check(
////        testName = "when month is after current month in current year should return error",
////        result = false,
////        acceptedResult = false
////    )
////    check(testName = "when month number is invalid should return error", result = false, acceptedResult = false)
////    check(testName = "when year number is invalid should return error", result = false, acceptedResult = false)
////    check(
////        testName = "when valid month with transactions should return correct summary",
////        result = false,
////        acceptedResult = false
////    )
////
//////endregion
////
////
////}
////
////fun <T> check(testName: String, result: T, acceptedResult: T) {
////    if (result == acceptedResult) println("Success - $testName")
////    else println("Fail - $testName")
////}
//
//import managers.TransactionManager
//import models.Category
//import saver.FileManagerImpl
//import saver.IFileManager
//import utils.ResultStatus
//import java.util.*
//
//
//fun main(){
//    val fileManger : IFileManager = FileManagerImpl()
//    val transactionManager = TransactionManager(fileManger)
//
////region Transactions Test Cases
////todo: write all test cases that related with transactions here :)
//    //val transactionManager = TransactionManager(fileManager = FileManagerImpl())
////    check(
////        testName = "when amount is less than or equal zero number should return false",
////        result = transactionManager.addTransaction(
////            Transaction(
////                id = UUID.randomUUID(), -1.0, "", date = LocalDate.now(),
////                Category(id = UUID.randomUUID(), ""), type = TransactionType.EXPENSE
////            )
////        ),
////        acceptedResult = ResultStatus.Error("please enter a valid amount number")
////    )
////    check(
////        testName = "when description is invalid like (containing only numbers,special characters) should return false",
////        result = transactionManager.addTransaction(
////            Transaction(
////                id = UUID.randomUUID(), 1.0, "11#$44", date = LocalDate.now(),
////                Category(id = UUID.randomUUID(), ""), type = TransactionType.EXPENSE
////            )
////        ),
////        acceptedResult = ResultStatus.Error("please enter a valid description")
////    )
////
////    //end region
////
//////region Transactions Test Cases
//////todo: write all test cases that related with transactions here :)
////
////    //region add transaction test cases
////    check(
////        testName = "when amount is less than or equal zero number should return false",
////        result = isValidInputAmount("0"),
////        acceptedResult = false
////    )
////    check(
////        testName = "when amount is letters or special characters should return false",
////        result = isValidInputAmount("a&"),
////        acceptedResult = false
////    )
////    check(
////        testName = "when description is invalid like (containing only numbers,special characters) should return false",
////        result = isValidDescription("1213$%#"),
////        acceptedResult = false
////    )
////    check(
////        testName = "when description is empty should return true",
////        result = isValidDescription(""),
////        acceptedResult = true
////    )
////    check(
////        testName = "when date is invalid should return false",
////        result = isValidDate("1-2-2024"),
////        acceptedResult = false
////    )
////    check(
////        testName = "when date is empty should return false",
////        result = isValidDate(""),
////        acceptedResult = false
////    )
////    check(
////        testName = "when transaction type is empty should return false",
////        result = isValidTransactionType(""),
////        acceptedResult = false
////    )
////    check(
////        testName = "when transaction type isn't one of these(INCOME,EXPENSE) should return false",
////        result = isValidTransactionType("ahmed"),
////        acceptedResult = false
////    )
////    //endregion
////
//    //region add transaction test cases
//    check(
//        testName = "when amount is less than or equal zero number should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "when amount is something else number should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "when id of transaction is already associated with another transaction should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "when description is invalid like (numbers,special characters) should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "when description is empty should return true",
//        result = true,
//        acceptedResult = true
//    )
//    check(
//        testName = "when date is invalid should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "when date is empty should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "when transaction type is empty should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "when category type is empty should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "when category is invalid like (numbers,special characters) should return false",
//        result = false,
//        acceptedResult = false
//    )
//    //endregion
//
//    // region delete transaction test cases
//    check(
//        testName = "when there is no any transaction added before should return false",
//        result = transactionManager.deleteTransaction(UUID.randomUUID()),
//        acceptedResult = ResultStatus.Error("Transaction not found")
//    )
//    check(
//        testName = "When entered id does not match the id schema should return false",
//        result = transactionManager.deleteTransaction(UUID.randomUUID()),
//        acceptedResult = ResultStatus.Error("Transaction not found")
//    )
//    check(
//        testName = "when entered id doesn't exist in the transactions should return false",
//        result = transactionManager.deleteTransaction(UUID.randomUUID()),
//        acceptedResult = ResultStatus.Error("Transaction not found")
//    )
//    check(
//        testName = "when entered id is less than zero should return false",
//        result = transactionManager.deleteTransaction(UUID.randomUUID()),
//        acceptedResult = ResultStatus.Error("Transaction not found")
//    )
//    // enter valid id please
//    check(
//        testName = "when entered id is founded should return true",
//        result = transactionManager.deleteTransaction(UUID.randomUUID()),
//        acceptedResult = ResultStatus.Success(true)
//    )
//    // end region
//    // region view transaction test cases
//    check(
//        testName = "when transaction id is not found then should return null",
//        result= false,
//        acceptedResult = false,
//    )
//    check(
//        testName = "when transaction id is found then should return transaction",
//        result= true,
//        acceptedResult = true,
//    )
//    check(
//        testName = "when transaction id is not valid then should return null",
//        result= false,
//        acceptedResult = false,
//    )
//    // endregion
////region Edit Transaction Test Cases
//
//    //endregion
////endregion
//
////region Category Test Cases
//
//    // region add Category Test Case
//    check(
//        testName = "When the user adds a category with a valid name should return true",
//        result = false,
//        acceptedResult =true ,
//    )
//    check(
//        testName = "When the user tries to add a category with the same name should return false ",
//        result = false,
//        acceptedResult =false ,
//    )
//    check(
//        testName = "When the user tries to add a category with an empty string should return false",
//        result = false,
//        acceptedResult =false ,
//    )
//    check(
//        testName = "When the user tries to add a category with special character should return false",
//        result = false,
//        acceptedResult =false ,
//    )
//    check(
//        testName = "When the user tries to add invalid category type should return false",
//        result = false,
//        acceptedResult =false ,
//    )
//    check(
//        testName = "When the user tries to add a category with spaces should return false",
//        result = false,
//        acceptedResult =false ,
//    )
//    //endregion
//
//
//
//    // region Edit Category Test Case
//    check(
//        testName = "When the user edit a category with a valid name and valid id should return true",
//        result = false,
//        acceptedResult =true ,
//    )
//    check(
//        testName = "When the user tries to edit a category with the same name and valid id should return false",
//        result = false,
//        acceptedResult =false ,
//    )
//    check(
//        testName = "When the user tries to edit a category with an empty string and valid id should return false",
//        result = false,
//        acceptedResult =false ,
//    )
//    check(
//        testName = "When the user tries to add a category with special character and invalid id (out of range) should return false",
//        result = false,
//        acceptedResult =false ,
//    )
//    check(
//        testName = "When the user tries to add invalid category type and valid id should return false",
//        result = false,
//        acceptedResult =false ,
//    )
//    check(
//        testName = "When the user tries to add a category with spaces and valid id should return false",
//        result = false,
//        acceptedResult =false ,
//    )
//    check(
//        testName = "When the user tries to add a valid category name and negative id should return false",
//        result = false,
//        acceptedResult =false ,
//    )
//    check(
//        testName = "When the user tries to add a valid category name and invalid type of id should return false",
//        result = false,
//        acceptedResult =false ,
//    )
//    //endregion
//
//
//
//    //region View Category Test Case
//    check(
//        testName = "When exist list of category should return true",
//        result = false,
//        acceptedResult = true
//    )
//    check(
//        testName = "When not exist list of category should return false",
//        result = false,
//        acceptedResult = false
//    )
//    //endregion
//
//
//
//    // region Delete Category Test Case
//    check(
//        testName = "When user enter valid id should return true",
//        result = false,
//        acceptedResult = true
//    )
//    check(
//        testName = "When user enter negative id should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "When user enter id equal zero should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "When user enter id out of range from id's that displayed should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "When user enter character rather than int should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "When user enter string rather than int should return false",
//        result = false,
//        acceptedResult = false
//    )
//    check(
//        testName = "When user enter space rather than int should return false",
//        result = false,
//        acceptedResult = false
//    )
//    //endregion
//
//
////endregion
//
////region view balance report test cases
//    check(
//        testName = "1. When there are no transactions in the date range, should return zero income, zero expenses, and zero net balance",
//        result = "",
//        acceptedResult = ""
//    )
//
//    check(
//        testName = "2. When only income transactions exist in the date range, should return correct income total and zero expenses",
//        result = "",
//        acceptedResult = ""
//    )
//
//    check(
//        testName = "3. When only expense transactions exist in the date range, should return correct expense total and zero income",
//        result = "",
//        acceptedResult = ""
//    )
//
//    check(
//        testName = "4. When both income and expense transactions exist, should return correct totals and net balance",
//        result = "",
//        acceptedResult = ""
//    )
//
//    check(
//        testName = "5. When start date is after end date, should return null",
//        result = null,
//        acceptedResult = ""
//    )
//
//    check(
//        testName = "6. When start or end date is invalid should return null",
//        result = null,
//        acceptedResult = ""
//    )
//
//    check(
//        testName = "7. When transactions fall exactly on the start or end date, should include them in the result",
//        result = "",
//        acceptedResult = ""
//    )
//
//    check(
//        testName = "8. When transactions exist but are completely outside the date range, should be excluded from the result",
//        result = "",
//        acceptedResult = ""
//    )
//
//    check(
//        testName = "9. When all transactions in the range have zero amounts, should return zero income, zero expense, and zero net balance",
//        result = "",
//        acceptedResult = ""
//    )
////endregion
//
//
////region Monthly Summary Test Cases
//
//    check(testName = "when no transactions in month should return NoTransactions", result = false, acceptedResult = false)
//    check(testName = "when year is after now should return error", result = false, acceptedResult = false)
//    check(testName = "when month is after current month in current year should return error", result = false, acceptedResult = false)
//    check(testName = "when month number is invalid should return error", result = false, acceptedResult = false)
//    check(testName = "when year number is invalid should return error", result = false, acceptedResult = false)
//    check(testName = "when valid month with transactions should return correct summary", result = false, acceptedResult = false)
//
////endregion
//
//}
////
////fun <T> check(testName: String, result: T, acceptedResult: T) {
////    if(result == acceptedResult) println("Success - $testName")
////    else println("Fail - $testName")
////}
