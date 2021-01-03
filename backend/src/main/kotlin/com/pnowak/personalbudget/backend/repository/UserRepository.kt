package com.pnowak.personalbudget.backend.repository

import com.pnowak.personalbudget.backend.entities.User
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository : PagingAndSortingRepository<User?, Long?> {
    fun findByUsername(username: String?): User?
    fun existsByEmail(email: String?): Boolean
    fun existsByUsername(username: String?): Boolean
}
