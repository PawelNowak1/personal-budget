package com.pnowak.personalbudget.backend.repository

import com.pnowak.personalbudget.backend.entities.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String?): User?
    fun existsByEmail(email: String?): Boolean
    fun existsByUsername(username: String?): Boolean
}
