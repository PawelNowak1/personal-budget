package com.pnowak.personalbudget.backend.controller

import com.pnowak.personalbudget.backend.dto.CategoryDTO
import com.pnowak.personalbudget.backend.dto.CreateCategoryDTO
import com.pnowak.personalbudget.backend.dto.MyUserDetails
import com.pnowak.personalbudget.backend.entities.Category
import com.pnowak.personalbudget.backend.service.interfaces.BudgetService
import com.pnowak.personalbudget.backend.service.interfaces.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/categories")
class CategoryController(val budgetService: BudgetService,
                         val categoryService: CategoryService) {

    fun getUserIdFromContext(): Long? {
        val authentication: MyUserDetails = SecurityContextHolder.getContext().authentication.principal as MyUserDetails
        return authentication.id
    }

    @GetMapping("/list")
    fun getCategories(): ResponseEntity<MutableList<CategoryDTO>> {
        return ResponseEntity.ok(budgetService.getCategoryList(getUserIdFromContext()))
    }

    @GetMapping("/onlyParent")
    fun getOnlyParentCategoryList(): ResponseEntity<List<Category>> {
        return ResponseEntity.ok(categoryService.getOnlyParentCategoryList(getUserIdFromContext()))
    }

    @PostMapping("/create")
    fun createParentCategory(@RequestBody createCategoryDTO: CreateCategoryDTO): ResponseEntity<Long?> {
        return ResponseEntity.ok(categoryService.createCategory(createCategoryDTO, getUserIdFromContext()!!))
    }


    @DeleteMapping("/delete/{categoryId}")
    fun deleteCategory(@PathVariable categoryId: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId))
    }

    @PostMapping("/update/{categoryId}")
    fun updateCategory(@RequestBody subcategoryName: String, @PathVariable categoryId: Long): ResponseEntity<Long?> {
        return ResponseEntity.ok(categoryService.updateCategory(subcategoryName, categoryId))
    }

    @PostMapping("/reorder/{categoryId}")
    fun reorderCategory(@RequestBody toIndex: Int, @PathVariable categoryId: Long): ResponseEntity<Long> {
        categoryService.reorderCategory(toIndex, categoryId, getUserIdFromContext()!!)
        return ResponseEntity.ok(1L)
    }
}
