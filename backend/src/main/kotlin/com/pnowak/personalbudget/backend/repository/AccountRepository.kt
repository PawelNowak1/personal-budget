package com.pnowak.personalbudget.backend.repository

import com.pnowak.personalbudget.backend.entities.Account
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.math.BigDecimal

interface AccountRepository : CrudRepository<Account, Long> {
    fun findAllByUserId(userId: Long?): List<Account>
    fun findAllByUserIdAndActiveIsTrue(userId: Long?): List<Account>
    @Query(value = "SELECT SUM(amount) from account where user_id = :user_id", nativeQuery = true)
    fun sumAmountByUserId(@Param("user_id") userId: Long?): BigDecimal
}
