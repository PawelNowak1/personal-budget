package com.pnowak.personalbudget.backend.service.impl

import com.pnowak.personalbudget.backend.dto.Budget
import com.pnowak.personalbudget.backend.mapper.BudgetMapper
import com.pnowak.personalbudget.backend.service.interfaces.BudgetService
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
class BudgetServiceImpl(private val jdbcTemplate: NamedParameterJdbcTemplate) : BudgetService {
    override fun getMonthYearBudget(month: Int, year: Int, userId: Long): MutableList<Budget?> {
        val sql = "SELECT\n" +
                "c.id as category_id,\n" +
                "       c.title as sub_category,\n" +
                "       (Select c2.title from category c2 where c2.id = c.id_parent) as category,\n" +
                "       sum(o.amount) as real_amount,\n" +
                "       bp.amount as budget_plan_amount,\n" +
                "       (coalesce(bp.amount, 0) - coalesce(sum(o.amount), 0)) as difference,\n" +
                "       c.order_num\n" +
                "FROM\n" +
                "     category c\n" +
                "LEFT JOIN budget_plan bp on c.id = bp.category_id and date_part('month', bp.month_year) = :month and date_part('year', bp.month_year) = :year\n" +
                "LEFT JOIN operation o on c.id = o.category_id and date_part('month', bp.month_year) = :month and date_part('year', bp.month_year) = :year\n" +
                "WHERE c.user_id = :userId and c.id_parent is not null\n" +
                "GROUP BY c.id, c.title, c.id, c.id_parent, bp.amount\n"
        return jdbcTemplate.query(sql, hashMapOf(Pair("month", month), Pair("year", year), Pair("userId", userId)), BudgetMapper())
    }
}
