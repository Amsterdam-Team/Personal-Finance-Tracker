package models.reports

import models.Category
import models.TransactionType

data class CategorySummary(
    val category: Category,
    val type: TransactionType,
    var totalAmount: Double
)