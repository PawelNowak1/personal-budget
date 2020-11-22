package com.pnowak.personalbudget.backend.controller

import com.pnowak.personalbudget.backend.dto.CategoryDTO
import com.pnowak.personalbudget.backend.dto.MyUserDetails
import com.pnowak.personalbudget.backend.service.interfaces.AccountService
import com.pnowak.personalbudget.backend.service.interfaces.BudgetService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/categories")
class CategoryController(val budgetService: BudgetService) {

    fun getUserIdFromContext(): Long? {
        val authentication: MyUserDetails = SecurityContextHolder.getContext().authentication.principal as MyUserDetails
        return authentication.id
    }

    @GetMapping("/list")
    fun getCategories(): ResponseEntity<MutableList<CategoryDTO>> {
        return ResponseEntity.ok(budgetService.getCategoryList(getUserIdFromContext()))
    }
}
