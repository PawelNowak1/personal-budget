package com.pnowak.personalbudget.backend.service.interfaces

import com.pnowak.personalbudget.backend.dto.Budget
import com.pnowak.personalbudget.backend.dto.CategoryDTO

interface BudgetService {
    fun getMonthYearBudget(month: Int, year: Int, userId: Long?): MutableList<Budget?>
    fun getCategoryList(userId: Long?): MutableList<CategoryDTO>
}
