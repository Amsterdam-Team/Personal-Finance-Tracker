package models

data class MonthlySummaryModel(
    val categorySummaries: List<CategorySummary>,
    val transactions:List<Transaction>
)
