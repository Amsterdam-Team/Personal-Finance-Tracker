package models

import java.time.LocalDate

data class Transaction(
    val id :Int,
    val amount:Double,
    val description:String,
    val date :LocalDate,
    val category: Category,
    val type: TransactionType
)
