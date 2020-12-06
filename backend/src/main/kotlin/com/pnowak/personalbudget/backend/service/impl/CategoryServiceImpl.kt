package com.pnowak.personalbudget.backend.service.impl

import com.pnowak.personalbudget.backend.dto.CreateCategoryDTO
import com.pnowak.personalbudget.backend.entities.Category
import com.pnowak.personalbudget.backend.repository.CategoryRepository
import com.pnowak.personalbudget.backend.repository.UserRepository
import com.pnowak.personalbudget.backend.service.interfaces.CategoryService
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl (private val categoryRepository: CategoryRepository,
                           private val userRepository: UserRepository,
                           private val jdbcTemplate: NamedParameterJdbcTemplate) : CategoryService {
    override fun createCategory(createCategoryDTO: CreateCategoryDTO, userId: Long): Long {
        val user = userRepository.findById(userId).get()
        val parentCategory: Category
        parentCategory = if (createCategoryDTO.parentCategoryName != null)
            categoryRepository.save(
                    Category(id = null, user = user, parent = null, title = createCategoryDTO.parentCategoryName!!, type = createCategoryDTO.categoryType, orderNum = getNextOrdNumberForParentCategory(userId)))
        else
            categoryRepository.findById(createCategoryDTO.parentCategoryId!!).get()


        val orderNum = getNextOrdNumberForSubcategory(userId)
        return categoryRepository.save(
                Category(id = null, user = user, parent = parentCategory, title = createCategoryDTO.subcategoryName, type = null, orderNum = orderNum)).id!!
    }

    override fun updateCategory(createCategoryDTO: CreateCategoryDTO, categoryId: Long, userIdFromContext: Long): Long {
        val category = categoryRepository.findById(categoryId)
//        category.get().title = createCategoryDTO.categoryName
//        category.get().type = createCategoryDTO.categoryType
//        category.get().orderNum = createCategoryDTO.orderNum
        return categoryRepository.save(category.get()).id!!
    }

    override fun getOnlyParentCategoryList(userIdFromContext: Long?): List<Category> {
        return categoryRepository.findAllByParentIsNullAndUserIdOrderByOrderNum(userIdFromContext!!)
    }

    override fun deleteCategory(categoryId: Long) {
        categoryRepository.deleteById(categoryId)
    }

    fun getNextOrdNumberForSubcategory(userId: Long): Int? {
        val sql = "SELECT MAX(order_num + 1)" +
                "from category where user_id = :user_id"
        var ordNumber = jdbcTemplate.queryForObject(sql, hashMapOf(Pair("user_id", userId)), Int::class.java)
        if (ordNumber == null)
            ordNumber = 1
        return ordNumber
    }

    fun getNextOrdNumberForParentCategory(userId: Long): Int {
        val sql = "SELECT MAX(order_num + 1)" +
                "from category where user_id = :user_id and id_parent is null"
        var ordNumber = jdbcTemplate.queryForObject(sql, hashMapOf(Pair("user_id", userId)), Int::class.java)
        if (ordNumber == null)
            ordNumber = 1
        return ordNumber
    }
}
