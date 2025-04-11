package test

import managers.CategoryManager
import managers.TransactionManager
import models.Category
import models.Transaction
import models.TransactionType
import models.reports.CategorySummary
import models.reports.MonthlySummary
import reports.MonthlySummaryManager
import saver.FileManagerImpl
import utils.ResultStatus
import utils.Validator
import java.time.LocalDate
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

    //endregion
    val carCategoryId = UUID.fromString("11111111-1111-1111-1111-111111111111")
    val salaryCategoryId = UUID.fromString("22222222-2222-2222-2222-222222222222")
    val rentCategoryId = UUID.fromString("33333333-3333-3333-3333-333333333333")

    val transaction1Id = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
    val transaction2Id = UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb")
    val transaction3Id = UUID.fromString("cccccccc-cccc-cccc-cccc-cccccccccccc")

    val carCategory = Category(id = carCategoryId, name = "car")
    val salaryCategory = Category(id = salaryCategoryId, name = "salary")
    val rentCategory = Category(id = rentCategoryId, name = "rent")

    val testTransactions = listOf(
        Transaction(id = transaction1Id, 5000.0, description = "Salary", date = LocalDate.of(2023,6,1), category = salaryCategory,type = TransactionType.INCOME),
        Transaction(id = transaction2Id, 200.0, description = "fuel", date =  LocalDate.of(2023,6,5), category = carCategory, type =  TransactionType.EXPENSE),
        Transaction(id = transaction3Id, 1000.0, description = "Rent", date = LocalDate.of(2023,5,1), category = rentCategory, type =  TransactionType.EXPENSE)
    )
    fileManager.getObjectById<Transaction>(transaction1Id.toString(), Transaction::class.java)
        ?.let {
            fileManager.saveObject(carCategory)
            fileManager.saveObject(salaryCategory)
            fileManager.saveObject(rentCategory)
            fileManager.saveObject(testTransactions[0])
            fileManager.saveObject(testTransactions[1])
            fileManager.saveObject(testTransactions[2])
        }


    check(
        "when no transactions in month should return Empty",
        MonthlySummaryManager(fileManager).getMonthlySummary(2023, 7),
        ResultStatus.Empty("There are no transactions this month")
    )

    check(
        "when year is after now should return Error",
        MonthlySummaryManager(fileManager).getMonthlySummary(LocalDate.now().year + 1, 6),
        ResultStatus.Error("Cannot view summary for future years")
    )

    check(
        "when month is after current month in current year should return Error",
        MonthlySummaryManager(fileManager).getMonthlySummary(LocalDate.now().year, LocalDate.now().monthValue + 1),
        ResultStatus.Error("Cannot view summary for future months")
    )

    check(
        "when month number is invalid should return Error",
        MonthlySummaryManager(fileManager).getMonthlySummary(2023, 13),
        ResultStatus.Error("Month must be between 1 and 12")
    )

    check(
        "when year number is invalid should return Error",
        MonthlySummaryManager(fileManager).getMonthlySummary(1999, 6),
        ResultStatus.Error("Year must be 2000 or later")
    )

    check(
        "when valid month with transactions should return correct summary",
        MonthlySummaryManager(fileManager).getMonthlySummary(2023, 6),
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
                    testTransactions[0],
                    testTransactions[1]
                )
            )
        )
    )
    //region Monthly summary test cases



//endregion


}

fun <T> check(testName: String, result: T, acceptedResult: T) {
    if(result == acceptedResult) println("Success - $testName")
    else println("Fail - $testName")
}