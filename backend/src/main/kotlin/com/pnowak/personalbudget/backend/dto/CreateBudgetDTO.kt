package com.pnowak.personalbudget.backend.dto

import java.math.BigDecimal
import java.util.*

data class CreateBudgetDTO(
        var plan: BigDecimal,
        var categoryId: Long,
        var monthYear: Date,
        var budgetPlanId: Long? = null
)
