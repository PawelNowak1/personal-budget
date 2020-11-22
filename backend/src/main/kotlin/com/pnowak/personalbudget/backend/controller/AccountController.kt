package com.pnowak.personalbudget.backend.controller

import com.pnowak.personalbudget.backend.dto.Budget
import com.pnowak.personalbudget.backend.dto.CategoryDTO
import com.pnowak.personalbudget.backend.dto.CreateAccountDTO
import com.pnowak.personalbudget.backend.dto.MyUserDetails
import com.pnowak.personalbudget.backend.entities.Account
import com.pnowak.personalbudget.backend.service.interfaces.AccountService
import com.pnowak.personalbudget.backend.service.interfaces.BudgetService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/account")
class AccountController(val accountService: AccountService) {

    fun getUserIdFromContext(): Long? {
        val authentication: MyUserDetails = SecurityContextHolder.getContext().authentication.principal as MyUserDetails
        return authentication.id
    }

    @PostMapping("/create")
    fun createAccount(@RequestBody createAccountDTO: CreateAccountDTO): ResponseEntity<Long> {
        return ResponseEntity.ok(accountService.createAccount(createAccountDTO, getUserIdFromContext()))
    }

    @GetMapping("/list")
    fun getCategories(): ResponseEntity<List<Account>> {
        return ResponseEntity.ok(accountService.getAccountList(getUserIdFromContext()))
    }
}
