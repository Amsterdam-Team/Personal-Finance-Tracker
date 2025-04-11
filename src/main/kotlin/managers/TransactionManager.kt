package managers

import Validators.checkIsValidDate
import Validators.checkIsValidDescription
import Validators.checkIsValidInputAmount
import Validators.checkIsValidTransactionType
import models.Transaction
import saver.FileManagerImpl
import utils.ResultStatus


class TransactionManager(private val fileManager: FileManagerImpl) {

    fun addTransaction(transaction: Transaction):ResultStatus<String>{
        return if (!checkIsValidInputAmount(transaction.amount.toString())){
            ResultStatus.Error("please enter a valid amount number")
        }else if (!checkIsValidDescription(transaction.description)){
            ResultStatus.Error("please enter a valid description")
        }else if(!checkIsValidDate(transaction.date.toString())) {
            ResultStatus.Error("please enter a valid date with that format : yyyy-MM-dd")
        }else if (!checkIsValidTransactionType(transaction.type.toString())){
            ResultStatus.Error("please enter one of these types only (INCOME,EXPENSE)")
        }
        else{
            fileManager.saveObject(transaction)
            ResultStatus.Success("Successfully added ur transaction with id : ${transaction.id}")
        }
    }
}

