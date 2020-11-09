package com.pnowak.personalbudget.backend.dto

import java.math.BigDecimal

data class Budget(
        var categoryId: Long? = null,
        var category: String? = null,
        var subcategory: String? = null,
        var plan: BigDecimal? = null,
        var real: BigDecimal? = null,
        var difference: BigDecimal? = null,
        var ordNumber: Int? = null
)
