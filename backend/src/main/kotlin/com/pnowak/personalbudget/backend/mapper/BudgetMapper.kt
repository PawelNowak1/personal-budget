package com.pnowak.personalbudget.backend.mapper

import com.pnowak.personalbudget.backend.dto.Budget
import com.pnowak.personalbudget.backend.enums.CategoryType
import org.springframework.jdbc.core.RowMapper
import java.math.BigDecimal
import java.sql.ResultSet
import java.sql.SQLException


class BudgetMapper : RowMapper<Budget?> {
    @Throws(SQLException::class)
    override fun mapRow(rs: ResultSet, rowNum: Int): Budget {
        val budget = Budget()
        budget.category = rs.getString("category")
        budget.subcategory = rs.getString("sub_category")
        budget.real = rs.getBigDecimal("real_amount")
        budget.plan = rs.getBigDecimal("budget_plan_amount")
        budget.categoryType = CategoryType.fromInt(rs.getInt("category_type"))
        if (budget.categoryType == CategoryType.EXPENSE)
            budget.difference = rs.getBigDecimal("difference")
        else
            budget.difference = BigDecimal.valueOf(-1) * rs.getBigDecimal("difference")

        budget.ordNumber = rs.getInt("order_num")
        budget.categoryId = rs.getLong("category_id")
        budget.parentOrdNumber = rs.getInt("parent_ord_number")
        budget.budgetPlanId = rs.getLong("budget_plan_id")
        return budget
    }
}
