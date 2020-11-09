package com.pnowak.personalbudget.backend.controller

import com.pnowak.personalbudget.backend.dto.Budget
import com.pnowak.personalbudget.backend.service.interfaces.BudgetService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/budget")
class BudgetController(val budgetService: BudgetService) {

    @GetMapping("/monthly-view")
    fun getCategories(@RequestParam(required = true) month: Int,
                      @RequestParam(required = true) year: Int,
                      @RequestParam(required = true) userId: Long): ResponseEntity<MutableList<Budget?>> {
        return ResponseEntity.ok(budgetService.getMonthYearBudget(month, year, userId))
    }
}
