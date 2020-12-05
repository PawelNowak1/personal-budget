package com.pnowak.personalbudget.backend.service.interfaces

import com.pnowak.personalbudget.backend.dto.CreateCategoryDTO
import com.pnowak.personalbudget.backend.entities.Category

interface CategoryService {
    fun createCategory(createCategoryDTO: CreateCategoryDTO, userId: Long): Long
    fun deleteCategory(categoryId: Long)
    fun updateCategory(createCategoryDTO: CreateCategoryDTO, categoryId: Long, userIdFromContext: Long): Long
    fun getOnlyParentCategoryList(userIdFromContext: Long?): List<Category>
}
