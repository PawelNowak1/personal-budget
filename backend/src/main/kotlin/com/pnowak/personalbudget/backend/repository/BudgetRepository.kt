package com.pnowak.personalbudget.backend.repository

import com.pnowak.personalbudget.backend.entities.BudgetPlan
import org.springframework.data.repository.CrudRepository

interface BudgetRepository : CrudRepository<BudgetPlan, Long> {
}
