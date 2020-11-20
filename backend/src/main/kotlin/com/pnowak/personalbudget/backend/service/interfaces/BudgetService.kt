package com.pnowak.personalbudget.backend.service.interfaces

import com.pnowak.personalbudget.backend.dto.Budget

interface BudgetService {
    fun getMonthYearBudget(month: Int, year: Int, userId: Long?): MutableList<Budget?>
}
