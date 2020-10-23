package com.pnowak.personalbudget.backend.entities

import javax.persistence.*

@Entity
@Table(name = "user_info")
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val username: String,
    val active: Boolean,
    val email: String,
    val password: String
)
