package models

data class MonthlySummaryReport (val expensesByCategory: Map<String,Int>,
                                 val totalIncome: Int)