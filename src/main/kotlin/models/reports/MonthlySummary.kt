package models.reports

import models.Transaction

data class MonthlySummary(
    val categorySummaries: List<CategorySummary>,
    val transactions:List<Transaction>
)
