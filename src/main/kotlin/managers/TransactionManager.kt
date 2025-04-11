package managers

import Validators.isValidDate
import Validators.isValidDescription
import Validators.isValidInputAmount
import Validators.isValidTransactionType
import models.Transaction
import saver.FileManagerImpl
import utils.ResultStatus


class TransactionManager(private val fileManager: FileManagerImpl) {

    fun addTransaction(transaction: Transaction): ResultStatus<String> {

        return when {
            !isValidInputAmount(transaction.amount.toString())
                -> ResultStatus.Error("please enter a valid amount number")

            !isValidDescription(transaction.description)
                -> ResultStatus.Error("please enter a valid description")

            !isValidDate(transaction.date.toString())
                -> ResultStatus.Error("please enter a valid date with that format : yyyy-MM-dd")

            !isValidTransactionType(transaction.type.toString())
                -> ResultStatus.Error("please enter one of these types only (INCOME,EXPENSE)")

            else -> {
                fileManager.saveObject(transaction)
                return ResultStatus.Success("Successfully added ur transaction with id : ${transaction.id}")
            }
        }

    }
}

