package com.pnowak.personalbudget.backend.repository

import com.pnowak.personalbudget.backend.entities.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository : PagingAndSortingRepository<User?, Long?> {
    fun findByUsername(username: String?): User?
    fun findByEmail(email: String?): User?
    fun existsByEmail(email: String?): Boolean
    fun existsByUsername(username: String?): Boolean
}
