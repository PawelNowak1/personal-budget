package com.pnowak.personalbudget.backend.service.interfaces

import com.pnowak.personalbudget.backend.dto.CreateCategoryDTO
import com.pnowak.personalbudget.backend.entities.Category

interface CategoryService {
    fun createCategory(createCategoryDTO: CreateCategoryDTO, userId: Long): Long
    fun deleteCategory(categoryId: Long)
    fun updateCategory(subcategoryName: String, categoryId: Long): Long
    fun getOnlyParentCategoryList(userIdFromContext: Long?): List<Category>
    fun reorderCategory(toIndex: Int, categoryId: Long, userIdFromContext: Long)
}
