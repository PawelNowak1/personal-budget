package com.pnowak.personalbudget.backend.dto

import com.pnowak.personalbudget.backend.enums.CategoryType

data class CategoryDTO(
        var categoryId: Long? = null,
        var category: String? = null,
        var subcategory: String? = null,
        var type: CategoryType,
        var parentOrdNumber: Int
)
