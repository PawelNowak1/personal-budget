package com.pnowak.personalbudget.backend.dto

import java.math.BigDecimal
import java.util.*

class CreateOperationDTO {
    var accountId: Long = -1
    var operationDate: Date = Date()
    var description: String? = ""
    var categoryId: Long = -1
    var amount: BigDecimal = BigDecimal.ZERO
}
