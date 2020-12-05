package com.pnowak.personalbudget.backend.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum class CategoryType(val type: String) {
    @JsonProperty("income")
    INCOME("INCOME"),
    @JsonProperty("expense")
    EXPENSE("EXPENSE");

    companion object {
        fun fromInt(value: Int) = CategoryType.values().first { it.ordinal == value }
    }
}
