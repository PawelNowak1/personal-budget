package com.pnowak.personalbudget.backend.dto

data class AccountDTO(
        var categoryId: Long? = null,
        var category: String? = null,
        var subcategory: String? = null
)
