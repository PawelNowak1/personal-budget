package com.pnowak.personalbudget.backend.dto

import com.pnowak.personalbudget.backend.enums.CategoryType

data class CreateCategoryDTO(
        var parentCategoryName: String?,
        var subcategoryName: String,
        var parentCategoryId: Long? = null,
        var categoryType: CategoryType? = null,
        var orderNum: Int? = null
)
