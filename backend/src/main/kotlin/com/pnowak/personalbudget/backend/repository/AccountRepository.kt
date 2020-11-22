package com.pnowak.personalbudget.backend.repository

import com.pnowak.personalbudget.backend.entities.Account
import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, Long> {
    fun findAllByUserId(userId: Long?): List<Account>
}
