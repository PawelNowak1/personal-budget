package com.pnowak.personalbudget.backend.dto

import com.pnowak.personalbudget.backend.enums.CategoryType
import java.math.BigDecimal

data class Budget(
        var categoryId: Long? = null,
        var category: String? = null,
        var subcategory: String? = null,
        var plan: BigDecimal? = null,
        var real: BigDecimal? = null,
        var difference: BigDecimal? = null,
        var ordNumber: Int? = null,
        var parentOrdNumber: Int? = null,
        var categoryType: CategoryType? = null
)
