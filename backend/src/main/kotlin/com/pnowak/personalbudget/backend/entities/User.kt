package com.pnowak.personalbudget.backend.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "user_info")
data class User (
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        val username: String?,
        var active: Boolean,
        val email: String?,
        @JsonIgnore
        val password: String
)
