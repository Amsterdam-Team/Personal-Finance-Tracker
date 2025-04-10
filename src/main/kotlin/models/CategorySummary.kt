package models

data class CategorySummary(
    val category: Category,
    val type: TransactionType,
    val totalAmount: Double
)