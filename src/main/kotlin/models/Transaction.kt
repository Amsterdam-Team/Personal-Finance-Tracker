package models

import java.time.LocalDate
import java.util.UUID

data class Transaction(
    val id :UUID,
    val amount:Double,
    val description:String,
    val date :LocalDate,
    val category: Category,
    val type: TransactionType
)
