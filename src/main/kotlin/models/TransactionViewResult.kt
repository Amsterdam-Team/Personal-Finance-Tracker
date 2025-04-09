package models

sealed class TransactionViewResult {
    data class Success(val transaction: Transaction): TransactionViewResult()

    data class Error(val message: String): TransactionViewResult()
}