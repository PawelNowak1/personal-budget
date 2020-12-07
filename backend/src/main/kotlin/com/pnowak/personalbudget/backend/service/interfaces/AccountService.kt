package com.pnowak.personalbudget.backend.service.interfaces

import com.pnowak.personalbudget.backend.dto.AccountDTO
import com.pnowak.personalbudget.backend.dto.CreateAccountDTO
import com.pnowak.personalbudget.backend.entities.Account
import java.math.BigDecimal

interface AccountService {
    fun createAccount(createAccountDTO: CreateAccountDTO, userId: Long?): Long
    fun getAccountList(onlyActive: Boolean?, userIdFromContext: Long?): List<Account>
    fun getSumAmountByUserId(userId: Long?): BigDecimal
}
