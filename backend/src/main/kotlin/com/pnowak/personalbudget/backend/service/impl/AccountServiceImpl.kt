package com.pnowak.personalbudget.backend.service.impl

import com.pnowak.personalbudget.backend.dto.AccountDTO
import com.pnowak.personalbudget.backend.dto.CreateAccountDTO
import com.pnowak.personalbudget.backend.entities.Account
import com.pnowak.personalbudget.backend.repository.AccountRepository
import com.pnowak.personalbudget.backend.repository.UserRepository
import com.pnowak.personalbudget.backend.security.JwtUtils
import com.pnowak.personalbudget.backend.service.interfaces.AccountService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

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
        return accountRepository.save(account).id
    }

    override fun getAccountList(userIdFromContext: Long?): List<Account> {
        return accountRepository.findAllByUserId(userIdFromContext)
    }
}
