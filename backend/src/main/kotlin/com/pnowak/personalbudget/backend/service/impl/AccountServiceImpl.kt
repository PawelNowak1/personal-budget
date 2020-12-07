package com.pnowak.personalbudget.backend.service.impl

import com.pnowak.personalbudget.backend.dto.CreateAccountDTO
import com.pnowak.personalbudget.backend.entities.Account
import com.pnowak.personalbudget.backend.repository.AccountRepository
import com.pnowak.personalbudget.backend.repository.UserRepository
import com.pnowak.personalbudget.backend.service.interfaces.AccountService
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AccountServiceImpl (private val accountRepository: AccountRepository,
                          private val userRepository: UserRepository) : AccountService {
    override fun createAccount(createAccountDTO: CreateAccountDTO, userId: Long?): Long {
        val user = userId?.let { userRepository.findById(it) }
        val account = Account()
        account.active = createAccountDTO.active
        account.amount = createAccountDTO.amount
        account.user = user?.get()
        account.currency = createAccountDTO.currency
        account.type = createAccountDTO.type
        account.name = createAccountDTO.name
        account.id = createAccountDTO.id
        return accountRepository.save(account).id!!
    }

    override fun getAccountList(onlyActive: Boolean?, userIdFromContext: Long?): List<Account> {
        if (onlyActive!!)
            return accountRepository.findAllByUserIdAndActiveIsTrue(userIdFromContext)
        return accountRepository.findAllByUserId(userIdFromContext)
    }

    override fun getSumAmountByUserId(userId: Long?): BigDecimal {
        return accountRepository.sumAmountByUserId(userId)
    }
}
