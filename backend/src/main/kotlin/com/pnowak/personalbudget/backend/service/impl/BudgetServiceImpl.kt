package com.pnowak.personalbudget.backend.service.impl

import com.pnowak.personalbudget.backend.dto.Budget
import com.pnowak.personalbudget.backend.dto.CategoryDTO
import com.pnowak.personalbudget.backend.enums.CategoryType
import com.pnowak.personalbudget.backend.mapper.BudgetMapper
import com.pnowak.personalbudget.backend.service.interfaces.BudgetService
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
class BudgetServiceImpl(private val jdbcTemplate: NamedParameterJdbcTemplate) : BudgetService {
    override fun getMonthYearBudget(month: Int, year: Int, userId: Long?): MutableList<Budget?> {
        val sql = "SELECT\n" +
                "c.id as category_id,\n" +
                "       c.title as sub_category,\n" +
                "       (Select c2.title from category c2 where c2.id = c.id_parent) as category,\n" +
                "       (Select c2.order_num from category c2 where c2.id = c.id_parent) as parent_ord_number,\n" +
                "       (Select c2.type from category c2 where c2.id = c.id_parent) as category_type,\n" +
                "       coalesce(sum(o.amount), 0) as real_amount,\n" +
                "       coalesce(bp.amount, 0) as budget_plan_amount,\n" +
                "       (coalesce(bp.amount, 0) - coalesce(sum(o.amount), 0)) as difference,\n" +
                "       c.order_num\n" +
                "FROM\n" +
                "     category c\n" +
                "LEFT JOIN budget_plan bp on c.id = bp.category_id and date_part('month', bp.month_year) = :month and date_part('year', bp.month_year) = :year\n" +
                "LEFT JOIN operation o on c.id = o.category_id and date_part('month', o.operation_date) = :month and date_part('year', o.operation_date) = :year\n" +
                "WHERE c.user_id = :userId and c.id_parent is not null\n" +
                "GROUP BY c.id, c.title, c.id, c.id_parent, bp.amount\n"
        return jdbcTemplate.query(sql, hashMapOf(Pair("month", month), Pair("year", year), Pair("userId", userId)), BudgetMapper())
    }

    override fun getCategoryList(userId: Long?): MutableList<CategoryDTO> {
        val sql = "SELECT\n" +
                "       c.id,\n" +
                "       c.type,\n" +
                "       c.title as sub_category,\n" +
                "       (select c2.title from category c2 where c2.id = c.id_parent) as category,\n" +
                "       (select c2.order_num from category c2 where c2.id = c.id_parent) as parentOrdNum\n" +
                "FROM\n" +
                "    category c where c.id_parent is not null and c.user_id = :user_id ORDER BY c.order_num"

        return jdbcTemplate.query(sql, hashMapOf(Pair("user_id", userId)), RowMapper { rs, _ ->
                CategoryDTO(rs.getLong("ID"), rs.getString("category"), rs.getString("sub_category"), CategoryType.fromInt(rs.getInt("type")), rs.getInt("parentOrdNum"))
            })
    }
}
