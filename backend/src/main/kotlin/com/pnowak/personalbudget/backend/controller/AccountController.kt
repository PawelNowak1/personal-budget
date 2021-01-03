package com.pnowak.personalbudget.backend.controller

import com.pnowak.personalbudget.backend.dto.CreateAccountDTO
import com.pnowak.personalbudget.backend.dto.MyUserDetails
import com.pnowak.personalbudget.backend.entities.Account
import com.pnowak.personalbudget.backend.service.interfaces.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal


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
    fun getAccountList(@RequestParam(required = false) onlyActive: Boolean): ResponseEntity<List<Account>> {
        return ResponseEntity.ok(accountService.getAccountList(onlyActive, getUserIdFromContext()))
    }

    @GetMapping("/sum")
    fun getSumAmountByUserId(): ResponseEntity<BigDecimal> {
        return ResponseEntity.ok(accountService.getSumAmountByUserId(getUserIdFromContext()))
    }
}
