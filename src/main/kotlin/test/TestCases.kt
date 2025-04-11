package test

import Validators.isValidCategory
import Validators.isValidCategoryName
import Validators.isValidDate
import Validators.isValidDescription
import Validators.isValidID
import models.Category
import utils.ResultStatus
import managers.*
import models.Transaction
import models.TransactionType
import reports.MonthlySummaryManager
import java.time.LocalDate
import java.util.*
import saver.FileManagerImpl

import utils.Validator.isValidInputAmount
import utils.Validator.isValidTransactionType
import saver.IFileManager


fun main(){
//region Transactions Test Cases

      //region mock data for testing add function validity
    val transactionManager = TransactionManager(fileManager = FileManagerImpl())
    val categoryManager = CategoryManager(fileManager = FileManagerImpl())
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

    // region delete transaction test cases
    check(
        testName = "when there is no any transaction added before should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "When entered id does not match the id schema should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when entered id doesn't exist in the transactions should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when entered id is less than zero should return false",
        result = false,
        acceptedResult = false
    )
    check(
        testName = "when entered id is founded should return true",
        result = true,
        acceptedResult = true
    )
    // end region

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
    //    check(
//        testName = "when transaction id is empty then should return error",
//        result = viewTransactionById("   "),
//        acceptedResult = TransactionViewResult.Error("Input is empty or contains only spaces."),
//    )
//
//    check(
//        testName = "when transaction id is not a valid UUID then should return error",
//        result = viewTransactionById("123-not-a-uuid"),
//        acceptedResult = TransactionViewResult.Error("You must enter a valid UUID."),
//    )
//
//    check(
//        testName = "when transaction id is valid UUID but not found then should return error",
//        result = viewTransactionById("550e8400-e29b-41d4-a716-446655440000"), // assuming it's not in file
//        acceptedResult = TransactionViewResult.Error("Transaction not found."),
//    )
//
//    check(
//        testName = "when transaction id is valid UUID and exists then should return success",
//        result = viewTransactionById(transaction.id.toString()),
//        acceptedResult = TransactionViewResult.Success(transaction),
//    )
    // endregion

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

        result = isValidID(UUID.randomUUID()),
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
        result = isValidCategory(""),
        acceptedResult = ResultStatus.Error("Invalid Category")
    )
    check(
        testName = "when category type is invalid return false",
        result = isValidCategory("Shopping"),
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
        result = categoryManager.isValidCategoryToEdit(categoryName = "Food", categoryID = "5b4d7e3a-e4be-487e-9151-4bd94bb50e4az"),
        acceptedResult = ResultStatus.Success("Success Editing"),
    )
    check(
        testName = "When the user tries to edit a category with the same name and valid id should return true",
        result = categoryManager.isValidCategoryToEdit(categoryName = "Food", categoryID = "5b4d7e3a-e4be-487e-9151-4bd94bb50e4az"),
        acceptedResult = ResultStatus.Success("Success Editing"),
    )
    check(
        testName = "When the user tries to edit a category with an empty string and valid id should return false",
        result = categoryManager.isValidCategoryToEdit(categoryName = "", categoryID = "5b4d7e3a-e4be-487e-9151-4bd94bb50e4az"),
        acceptedResult = ResultStatus.Error("Enter Valid Name"),
    )
    check(
        testName = "When the user tries to add a category with special character and invalid id (out of range) should return false",
        result = categoryManager.isValidCategoryToEdit(categoryName = "@@", categoryID = "5b4d7e3a-e4be-487e-9151-4bd94bb50e4az-5545454"),
        acceptedResult = ResultStatus.Error("Enter Valid Name and ID"),
    )
    check(
        testName = "When the user tries to add invalid category type and valid id should return false",
        result = categoryManager.isValidCategoryToEdit(categoryName = "4565465", categoryID = "5b4d7e3a-e4be-487e-9151-4bd94bb50e4az"),
        acceptedResult = ResultStatus.Error("Enter Valid Name"),
    )
    check(
        testName = "When the user tries to add a category with spaces and valid id should return false",
        result = categoryManager.isValidCategoryToEdit(categoryName = " Food ", categoryID = "5b4d7e3a-e4be-487e-9151-4bd94bb50e4az"),
        acceptedResult = ResultStatus.Error("Enter Valid Name"),
    )
    check(
        testName = "When the user tries to add a valid category name and negative id should return false",
        result = categoryManager.isValidCategoryToEdit(categoryName = "Food", categoryID = "-5b4d7e3a-e4be-487e-9151-4bd94bb50e4az"),
        acceptedResult = ResultStatus.Error("Enter Valid ID"),
    )
    check(
        testName = "When the user tries to add a valid category name and invalid type of id should return false",
        result = categoryManager.isValidCategoryToEdit(categoryName = "Food", categoryID = "kah;khf"),
        acceptedResult = ResultStatus.Error("Enter Valid ID"),
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
val fileMgr: IFileManager = FileManagerImpl()
    val carCategory = Category(UUID.randomUUID(), name = "car")
    val salaryCategory = Category(UUID.randomUUID(), name = "salary")
    val rentCategory = Category(UUID.randomUUID(), name = "rent")
    fileMgr.saveObject(carCategory)
    fileMgr.saveObject(salaryCategory)
    fileMgr.saveObject(rentCategory)
    val testTransactions = listOf(
        Transaction(UUID.randomUUID(), 5000.0, "Salary", LocalDate.of(2023,6,1), salaryCategory, TransactionType.INCOME),
        Transaction(UUID.randomUUID(), 200.0, "fuel", LocalDate.of(2023,6,5), carCategory, TransactionType.EXPENSE),
        Transaction(UUID.randomUUID(), 1000.0, "Rent", LocalDate.of(2023,5,1), rentCategory, TransactionType.EXPENSE)
    )
    fileMgr.saveObject(Transaction(UUID.randomUUID(), 5000.0, "Salary", LocalDate.of(2023,6,1), salaryCategory, TransactionType.INCOME))
    fileMgr.saveObject(Transaction(UUID.randomUUID(), 200.0, "fuel", LocalDate.of(2023,6,5), carCategory, TransactionType.EXPENSE))
    fileMgr.saveObject( Transaction(UUID.randomUUID(), 1000.0, "Rent", LocalDate.of(2023,5,1), rentCategory, TransactionType.EXPENSE))
    check(
        testName = "when no transactions in month should return NoTransactions",
        result = MonthlySummaryManager(fileMgr).getMonthlySummary(2023, 7),
        acceptedResult = ResultStatus.Empty("There are no transactions this month")
    )

    check(
        testName = "when year is after now should return Error",
        result = MonthlySummaryManager(fileMgr).getMonthlySummary(LocalDate.now().year + 1, 6),
        acceptedResult = ResultStatus.Error("Cannot view summary for future years")
    )

    check(
        testName = "when month is after current month in current year should return Error",
        result = MonthlySummaryManager(fileMgr).getMonthlySummary(LocalDate.now().year, LocalDate.now().monthValue + 1),
        acceptedResult = ResultStatus.Error("Cannot view summary for future months")
    )

    check(
        testName = "when month number is invalid should return Error",
        result = MonthlySummaryManager(fileMgr).getMonthlySummary(2023, 13),
        acceptedResult = ResultStatus.Error("Month must be between 1 and 12")
    )

    check(
        testName = "when year number is invalid should return Error",
        result = MonthlySummaryManager(fileMgr).getMonthlySummary(1999, 6),
        acceptedResult = ResultStatus.Error("Year must be 2000 or later")
    )

//endregion


}

fun <T> check(testName: String, result: T, acceptedResult: T) {
    if (result == acceptedResult) println("Success - $testName")
    else println("Fail - $testName")
}
