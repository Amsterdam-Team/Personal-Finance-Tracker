package models

data class CategorySummary(
    val category: Category,
    val type: TransactionType,
    var totalAmount: Double
)