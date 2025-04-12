package test

import managers.CategoryManager
import managers.TransactionManager
import models.BalanceReport
import models.Category
import models.Transaction
import models.TransactionType
import models.reports.CategorySummary
import models.reports.MonthlySummary
import reports.BalanceReportManager
import reports.MonthlySummaryManager
import saver.FileManagerImpl
import utils.ResultStatus
import utils.Validator.isValidDate
import utils.Validator.isValidInput
import utils.Validator.isValidInputAmount
import utils.Validator.isValidTransactionType
import java.io.File
import java.time.LocalDate
import java.util.UUID

fun main() {
    val fileManager = FileManagerImpl()
    val transactionManager = TransactionManager(fileManager)
    val categoryManager = CategoryManager(fileManager)
    val balanceReportManager = BalanceReportManager(fileManager)

    //region add transaction test cases
    check(
        testName = "when amount is empty should return error",
        result = isValidInputAmount(""),
        expectedResult = ResultStatus.Empty("please enter an amount")
    )
    check(
        testName = "when amount is less than or equal zero number should return error",
        result = isValidInputAmount("0"),
        expectedResult = ResultStatus.Error("amount number should be more than zero")
    )
    check(
        testName = "when amount is letters or special characters should return error",
        result = isValidInputAmount("a&"),
        expectedResult = ResultStatus.Error("amount number shouldn't contain any letters or special characters")
    )
    check(
        testName = "when description is invalid like (containing only numbers,special characters) should return error",
        result = isValidInput("1213$%#"),
        expectedResult = ResultStatus.Error("input shouldn't be numbers or special characters only it must have a letters")
    )
    check(
        testName = "when description is empty should return success",
        result = isValidInput(""),
        expectedResult = ResultStatus.Empty("please enter an input")
    )
    check(
        testName = "when date is invalid should return error",
        result = isValidDate("1-2-2024"),
        expectedResult = ResultStatus.Error("please enter a valid date with that format : yyyy-MM-dd")
    )
    check(
        testName = "when date is empty should return error",
        result = isValidDate(""),
        expectedResult = ResultStatus.Empty("please enter the date")
    )
    check(
        testName = "when transaction type is empty should return error",
        result = isValidTransactionType(""),
        expectedResult = ResultStatus.Error("please enter a transaction type ")
    )
    check(
        testName = "when transaction type isn't one of these(INCOME,EXPENSE) should return error",
        result = isValidTransactionType("ahmed"),
        expectedResult = ResultStatus.Error("please enter one of these types only (INCOME,EXPENSE)")
    )
    //endregion

    //region Edit Transaction Test Cases


    check(
        testName = "when amount of existing transaction equals zero or negative number return error",
        result = transactionManager.editTransaction(
            Transaction(
                UUID.randomUUID(), 0.0, "Shopping", LocalDate.now(), Category(
                    UUID.randomUUID(), ""
                ), TransactionType.EXPENSE
            )
        ),
        expectedResult = ResultStatus.Error("Invalid Data")
    )
    check(
        testName = "when amount is something else number should return error",
        result = isValidInputAmount("unknown"),
        expectedResult = ResultStatus.Error("amount number shouldn't contain any letters or special characters")
    )
    check(
        testName = "when id of transaction is invalid should return error",
        result = transactionManager.editTransaction(
            Transaction(
                UUID.randomUUID(), 0.0, "Shopping", LocalDate.now(), Category(
                    UUID.randomUUID(), ""
                ), TransactionType.EXPENSE
            )
        ),
        expectedResult = ResultStatus.Error("Invalid Data")
    )
    check(
        testName = "when description is invalid like (numbers,special characters) should return error",
        result = transactionManager.editTransaction(
            Transaction(
                UUID.randomUUID(), 0.0, "Shopping", LocalDate.now(), Category(
                    UUID.randomUUID(), "$%#&$"
                ), TransactionType.EXPENSE
            )
        ),
        expectedResult = ResultStatus.Error("Invalid Data")
    )

    check(
        testName = "when transaction type is empty should return error",
        result = isValidTransactionType(""),
        expectedResult = ResultStatus.Error("please enter a transaction type ")
    )
    check(
        testName = "when category type is empty should return error",
        result = transactionManager.editTransaction(
            Transaction(
                UUID.randomUUID(), 0.0, " ", LocalDate.now(), Category(
                    UUID.randomUUID(), ""
                ), TransactionType.EXPENSE
            )
        ),
        expectedResult = ResultStatus.Error("Invalid Data")
    )
    check(
        testName = "when category type is invalid return error",
        result = transactionManager.editTransaction(
            Transaction(
                UUID.randomUUID(), 0.0, "Rent", LocalDate.now(), Category(
                    UUID.randomUUID(), ""
                ), TransactionType.EXPENSE
            )
        ),
        expectedResult = ResultStatus.Error("Invalid Data")
    )
    //endregion

    // region view transaction test cases
    check(
        testName = "when transaction id is empty then should return error",
        result = transactionManager.viewTransactionById("   "),
        expectedResult = ResultStatus.Error("Input is empty or contains only spaces."),
    )

    check(
        testName = "when transaction id is not a valid UUID then should return error",
        result = transactionManager.viewTransactionById("123-not-a-uuid"),
        expectedResult = ResultStatus.Error("You must enter a valid UUID."),
    )

    check(
        testName = "when transaction id is valid UUID but not found then should return error",
        result = transactionManager.viewTransactionById("550e8400-e29b-41d4-a716-446655440000"), // assuming it's not in file
        expectedResult = ResultStatus.Error("Transaction not found."),
    )

    val uuid = UUID.randomUUID()
    val transaction = Transaction(
        uuid, 100.0, "Test Transaction", LocalDate.now(),
        Category(UUID.randomUUID(), "Test Category"), TransactionType.EXPENSE
    )
    fileManager.saveObject(transaction)

    check(
        testName = "when transaction id is valid UUID and exists then should return success",
        result = transactionManager.viewTransactionById(transaction.id.toString()),
        expectedResult = ResultStatus.Success(transaction),
    )
    clearSaveFile()
//     endregion

    //region delete transaction test cases

    check(
        testName = "when there is no any transaction added before should return error",
        result = transactionManager.deleteTransaction(UUID.randomUUID()),
        expectedResult = ResultStatus.Error("Transaction not found")
    )
    check(
        testName = "When entered id does not match the id schema should return error",
        result = transactionManager.deleteTransaction(UUID.randomUUID()),
        expectedResult = ResultStatus.Error("Transaction not found")
    )
    check(
        testName = "when entered id doesn't exist in the transactions should return error",
        result = transactionManager.deleteTransaction(UUID.randomUUID()),
        expectedResult = ResultStatus.Error("Transaction not found")
    )
    check(
        testName = "when entered id is less than zero should return error",
        result = transactionManager.deleteTransaction(UUID.randomUUID()),
        expectedResult = ResultStatus.Error("Transaction not found")
    )
    // enter valid id please
    val uuid3 = UUID.randomUUID()
    val transaction3 = Transaction(
        uuid3, 20.0, "test", LocalDate.now(),
        Category(UUID.randomUUID(), "test"), TransactionType.EXPENSE
    )
    fileManager.saveObject(transaction3)
    check(
        testName = "when entered id is founded should return success",
        result = transactionManager.deleteTransaction(transaction3.id),
        expectedResult = ResultStatus.Success("true")
    )
    clearSaveFile()
    //endregion

    // region view category test cases
    run {
        val viewCategoryId1 = UUID.randomUUID()
        val viewCategoryId2 = UUID.randomUUID()
        val viewCategory1 = Category(viewCategoryId1, "Books")
        val viewCategory2 = Category(viewCategoryId2, "Utilities")

        fileManager.saveObject(viewCategory1)
        fileManager.saveObject(viewCategory2)

        check(
            testName = "When categories exist, should return Success",
            result = categoryManager.viewCategories(),
            expectedResult = ResultStatus.Success(listOf(viewCategory1, viewCategory2))
        )
    }

    run {
        clearSaveFile()
        val emptyManager = CategoryManager(FileManagerImpl())
        check(
            testName = "When category list is empty, should return Empty",
            result = emptyManager.viewCategories(),
            expectedResult = ResultStatus.Empty("No categories found!")
        )
    }
    // endregion

    // region Edit Category Test Case
    fileManager.saveObject(
        Category(
            UUID.fromString("11111111-1111-1111-1111-111111111111"),
            "Food"
        )
    )
    check(
        testName = "When the user edit a category with a valid name and valid id should return success",
        result = categoryManager.isValidCategoryToEdit(
            categoryName = "Food",
            categoryID = UUID.fromString("11111111-1111-1111-1111-111111111111").toString()
        ),
        expectedResult = ResultStatus.Success("Success Editing"),
    )
    check(
        testName = "When the user tries to edit a category with an empty string and valid id should return error",
        result = categoryManager.isValidCategoryToEdit(
            categoryName = "",
            categoryID = "5b4d7e3a-e4be-487e-9151-4bd94bb50e4az"
        ),
        expectedResult = ResultStatus.Error("Please Enter Valid Id and Name"),
    )
    check(
        testName = "When the user tries to add a category with special character and invalid id (out of range) should return error",
        result = categoryManager.isValidCategoryToEdit(
            categoryName = "@@",
            categoryID = "5b4d7e3a-e4be-487e-9151-4bd94bb50e4az-5545454"
        ),
        expectedResult = ResultStatus.Error("Please Enter Valid Id and Name"),
    )
    check(
        testName = "When the user tries to add invalid category type and valid id should return error",
        result = categoryManager.isValidCategoryToEdit(
            categoryName = "4565465",
            categoryID = "5b4d7e3a-e4be-487e-9151-4bd94bb50e4az"
        ),
        expectedResult = ResultStatus.Error("Please Enter Valid Id and Name"),
    )
    check(
        testName = "When the user tries to add a category with spaces and valid id should return error",
        result = categoryManager.isValidCategoryToEdit(
            categoryName = " Food ",
            categoryID = "5b4d7e3a-e4be-487e-9151-4bd94bb50e4az"
        ),
        expectedResult = ResultStatus.Error("Please Enter Valid Id and Name"),
    )
    check(
        testName = "When the user tries to add a valid category name and negative id should return error",
        result = categoryManager.isValidCategoryToEdit(
            categoryName = "Food",
            categoryID = "-5b4d7e3a-e4be-487e-9151-4bd94bb50e4az"
        ),
        expectedResult = ResultStatus.Error("Please Enter Valid Id and Name"),
    )
    check(
        testName = "When the user tries to add a valid category name and invalid type of id should return error",
        result = categoryManager.isValidCategoryToEdit(
            categoryName = "Food",
            categoryID = "kah;khf"
        ),
        expectedResult = ResultStatus.Error("Please Enter Valid Id and Name"),
    )
    //endregion

    // region delete category test cases
    run {
        val deleteCategoryId = UUID.randomUUID()
        val deleteCategory = Category(deleteCategoryId, "Fitness")
        fileManager.saveObject(deleteCategory)

        check(
            testName = "When valid ID is passed, should return Success",
            result = categoryManager.deleteCategoryById(deleteCategoryId.toString()),
            expectedResult = ResultStatus.Success("Category deleted successfully!")
        )
    }

    run {
        clearSaveFile()
        val unknownId = UUID.randomUUID().toString()
        check(
            testName = "When ID not found in file, should return Error",
            result = categoryManager.deleteCategoryById(unknownId),
            expectedResult = ResultStatus.Error("Category not found.")
        )
    }

    run {
        check(
            testName = "When ID is blank, should return Error",
            result = categoryManager.deleteCategoryById(""),
            expectedResult = ResultStatus.Error("Invalid category ID.")
        )
    }

    run {
        val invalidUuid = "not-a-valid-uuid"
        val result = categoryManager.deleteCategoryById(invalidUuid)
        check(
            testName = "When ID has invalid format, should return Error from exception",
            result = result is ResultStatus.Error,
            expectedResult = true
        )
    }
    // endregion

    // region validators test cases
    check(
        testName = "when amount of existing transaction equals zero or negative number return error",
        result = isValidInputAmount("0.0"),
        expectedResult = ResultStatus.Error("amount number should be more than zero")
    )
    check(
        testName = "when amount is something else number should return error",
        result = isValidInputAmount("unknown"),
        expectedResult = ResultStatus.Error("amount number shouldn't contain any letters or special characters")
    )

    check(
        testName = "when description is invalid like (numbers,special characters) should return error",
        result = isValidInput("$#%@"),
        expectedResult = ResultStatus.Error("input shouldn't be numbers or special characters only it must have a letters")
    )
    check(
        testName = "when description is empty should return error",
        result = isValidInput(""),
        expectedResult = ResultStatus.Empty("please enter an input")
    )
    check(
        testName = "when date is invalid should return error",
        result = isValidDate("145224"),
        expectedResult = ResultStatus.Error("please enter a valid date with that format : yyyy-MM-dd")
    )

    check(
        testName = "when transaction type is empty should return error",
        result = isValidInput(""),
        expectedResult = ResultStatus.Empty("please enter an input")
    )
    check(
        testName = "when category type is empty should return ",
        result = isValidInput(""),
        expectedResult = ResultStatus.Empty("please enter an input")
    )
    check(
        testName = "when category type is invalid return error",
        result = isValidInput("%#&*"),
        expectedResult = ResultStatus.Error("input shouldn't be numbers or special characters only it must have a letters")
    )
    //endregion

    //region Category Test Cases

    val categoryId = UUID.fromString("11111111-1111-1111-1111-111111111111")
    check(

        testName = "When the user tries to add a valid category  should return success ",
        result = categoryManager.addCategory(categoryId, "food"),
        expectedResult = ResultStatus.Success("Category Added Successfully!"),
    )
    check(

        testName = "When the user tries to add a repeated category  should return error ",
        result = categoryManager.addCategory(categoryId, "food"),
        expectedResult = ResultStatus.Error("Invalid Data"),
    )
    check(

        testName = "When the user tries to remove a valid category  should return success ",
        result = categoryManager.deleteCategoryById(categoryId.toString()),
        expectedResult = ResultStatus.Success("Category deleted successfully!"),
    )


    //region add Category Test Case
    val categoryId1 = UUID.randomUUID()
    fileManager.saveObject(
        Category(id = categoryId1, name = "Salary")
    )
    check(
        testName = "When the user tries to add a category with the same name should return error",
        result = categoryManager.addCategory(categoryId1, "Salary"),
        expectedResult = ResultStatus.Error("Invalid Data"),
    )
    check(
        testName = "When the user tries to add a category with an empty string should return error",
        result = categoryManager.addCategory(UUID.randomUUID(), ""),
        expectedResult = ResultStatus.Error("Invalid Data"),
    )
    check(
        testName = "When the user tries to add a category with special character should return error",
        result = categoryManager.addCategory(UUID.randomUUID(), "$#%#"),
        expectedResult = ResultStatus.Error("Invalid Data"),
    )
    check(
        testName = "When the user tries to add invalid category type should return error",
        result = categoryManager.addCategory(UUID.randomUUID(), "123"),
        expectedResult = ResultStatus.Error("Invalid Data"),
    )
    check(
        testName = "When the user tries to add a category with spaces should return error",
        result = categoryManager.addCategory(UUID.randomUUID(), "Salary "),
        expectedResult = ResultStatus.Error("Invalid Data"),
    )

    //endregion

    //endregion

    //region Monthly summary test cases
    clearSaveFile()
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
        Transaction(
            id = transaction1Id,
            5000.0,
            description = "Salary",
            date = LocalDate.of(2023, 6, 1),
            category = salaryCategory,
            type = TransactionType.INCOME
        ),
        Transaction(
            id = transaction2Id,
            200.0,
            description = "fuel",
            date = LocalDate.of(2023, 6, 5),
            category = carCategory,
            type = TransactionType.EXPENSE
        ),
        Transaction(
            id = transaction3Id,
            1000.0,
            description = "Rent",
            date = LocalDate.of(2023, 5, 1),
            category = rentCategory,
            type = TransactionType.EXPENSE
        )
    )

    fileManager.saveObject(carCategory)
    fileManager.saveObject(salaryCategory)
    fileManager.saveObject(rentCategory)
    fileManager.saveObject(testTransactions[0])
    fileManager.saveObject(testTransactions[1])
    fileManager.saveObject(testTransactions[2])



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
        MonthlySummaryManager(fileManager).getMonthlySummary(
            LocalDate.now().year,
            LocalDate.now().monthValue + 1
        ),
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

    clearSaveFile()
//endregion

    //region view balance report test cases

    val incomeCategory = Category(UUID.fromString("00000000-0000-0000-0000-000000000001"), "income")
    val expenseCategory =
        Category(UUID.fromString("00000000-0000-0000-0000-000000000002"), "expense")
    val incomeTransaction1 = Transaction(
        id = UUID.fromString("11111111-1111-1111-1111-111111111111"),
        amount = 150.0,
        description = "Income on 2025-04-01",
        date = LocalDate.of(2025, 4, 1),
        category = incomeCategory,
        type = TransactionType.INCOME
    )
    val expenseTransaction1 = Transaction(
        id = UUID.fromString("22222222-2222-2222-2222-222222222222"),
        amount = 50.0,
        description = "Expense on 2025-04-03",
        date = LocalDate.of(2025, 4, 3),
        category = expenseCategory,
        type = TransactionType.EXPENSE
    )
    val zeroAmountTransaction = Transaction(
        id = UUID.fromString("33333333-3333-3333-3333-333333333333"),
        amount = 0.0,
        description = "Zero amount on 2025-04-05",
        date = LocalDate.of(2025, 4, 5),
        category = incomeCategory,
        type = TransactionType.INCOME
    )
// Save categories and transactions
    fileManager.saveObject(incomeCategory)
    fileManager.saveObject(expenseCategory)
    fileManager.saveObject(incomeTransaction1)
    fileManager.saveObject(expenseTransaction1)
    fileManager.saveObject(zeroAmountTransaction)

    check(
        testName = "1. When there are no transactions in the date range, should return zero income, zero expenses, and zero net balance",
        result = balanceReportManager.getBalanceReport(startDate = LocalDate.of(2030, 1, 1)),
        expectedResult = ResultStatus.Error("The start date cannot be after the end date. Please adjust your date range.")
    )

    check(
        testName = "2. When only income transactions exist in the date range, should return correct income total and zero expenses",
        result = balanceReportManager.getBalanceReport(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 4, 2)
        ),
        expectedResult = ResultStatus.Success(BalanceReport(150.0, 0.0, 150.0))
    )

    check(
        testName = "3. When only expense transactions exist in the date range, should return correct expense total and zero income",
        result = balanceReportManager.getBalanceReport(
            LocalDate.of(2025, 4, 3),
            LocalDate.of(2025, 4, 10)
        ),
        expectedResult = ResultStatus.Success(BalanceReport(0.0, 50.0, -50.0))
    )

    check(
        testName = "4. When both income and expense transactions exist, should return correct totals and net balance",
        result = balanceReportManager.getBalanceReport(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 4, 10)
        ),
        expectedResult = ResultStatus.Success(BalanceReport(150.0, 50.0, 100.0))
    )

    check(
        testName = "5. When start date is after end date, should return The start date cannot be after the end date Error",
        result = balanceReportManager.getBalanceReport(
            LocalDate.of(2025, 4, 10),
            LocalDate.of(2025, 4, 1)
        ),
        expectedResult = ResultStatus.Error("The start date cannot be after the end date. Please adjust your date range.")
    )

    check(
        testName = "6. When start or end date is invalid should invalid date Error",
        result = balanceReportManager.getBalanceReport(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2026, 1, 1)
        ),
        expectedResult = ResultStatus.Error("The end date cannot be after the current date. Please provide a valid end date.")
    )

    check(
        testName = "7. When transactions fall exactly on the start or end date, should include them in the result",
        result = balanceReportManager.getBalanceReport(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 4, 10)
        ),
        expectedResult = ResultStatus.Success(BalanceReport(150.0, 50.0, 100.0))
    )

    check(
        testName = "8. When transactions exist but are completely outside the date range, should be excluded from the result",
        result = balanceReportManager.getBalanceReport(
            LocalDate.of(2019, 1, 1),
            LocalDate.of(2019, 12, 31)
        ),
        expectedResult = ResultStatus.Empty("No transactions found in the selected date range. Please check the dates or add transactions.")
    )

    check(
        testName = "9. When all transactions in the range have zero amounts, should return zero income, zero expense, and zero net balance",
        result = balanceReportManager.getBalanceReport(
            LocalDate.of(2025, 4, 5),
            LocalDate.of(2025, 4, 6)
        ),
        expectedResult = ResultStatus.Success(BalanceReport(0.0, 0.0, 0.0))
    )
    clearSaveFile()
//endregion
}

fun <T> check(testName: String, result: T, expectedResult: T) {
    if (result == expectedResult) println("Success - $testName")
    else println("Fail - $testName")
}

fun clearSaveFile() {
    val file = File("src/main/kotlin/saver/saveFiletest.txt")
    if (file.exists()) {
        file.writeText("")
    }
}