package domain

import data.DataSource
import models.MonthlySummaryReport
import models.Response
import models.Transaction
import java.time.LocalDate


class ReportDomainService(val dataSource: DataSource) {
    fun getMonthlySummaryReport(month: LocalDate): Response<MonthlySummaryReport>{
        //TODO

        throw Exception()

    }
    fun getBalanceReport() : Response<String>{
        //TODO
        throw Exception()

    }
}