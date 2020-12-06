package com.pnowak.personalbudget.backend.controller

import com.pnowak.personalbudget.backend.dto.Budget
import com.pnowak.personalbudget.backend.dto.CreateBudgetDTO
import com.pnowak.personalbudget.backend.dto.MyUserDetails
import com.pnowak.personalbudget.backend.service.interfaces.BudgetService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/budget")
class BudgetController(val budgetService: BudgetService) {

    fun getUserIdFromContext(): Long? {
        val authentication: MyUserDetails = SecurityContextHolder.getContext().authentication.principal as MyUserDetails
        return authentication.id
    }

    @GetMapping("/monthly-view")
    fun getMonthlyView(@RequestParam(required = true) month: Int,
                      @RequestParam(required = true) year: Int): ResponseEntity<MutableList<Budget?>> {
        return ResponseEntity.ok(budgetService.getMonthYearBudget(month, year, getUserIdFromContext()))
    }

    @PostMapping("/create")
    fun createBudget(@RequestBody createBudgetDTO: CreateBudgetDTO): ResponseEntity<Long?> {
        return ResponseEntity.ok(budgetService.createBudget(createBudgetDTO))
    }
}
