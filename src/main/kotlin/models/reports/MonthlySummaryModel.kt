package models.reports

import models.Transaction

data class MonthlySummaryModel(
    val categorySummaries: List<CategorySummary>,
    val transactions:List<Transaction>
)
