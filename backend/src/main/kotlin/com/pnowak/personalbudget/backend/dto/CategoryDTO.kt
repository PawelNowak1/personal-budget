package com.pnowak.personalbudget.backend.dto

data class CategoryDTO(
        var categoryId: Long? = null,
        var category: String? = null,
        var subcategory: String? = null
)
