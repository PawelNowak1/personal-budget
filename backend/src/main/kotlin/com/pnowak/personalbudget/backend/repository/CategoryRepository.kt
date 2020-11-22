package com.pnowak.personalbudget.backend.repository

import com.pnowak.personalbudget.backend.entities.Account
import com.pnowak.personalbudget.backend.entities.Category
import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<Category, Long> {
}
