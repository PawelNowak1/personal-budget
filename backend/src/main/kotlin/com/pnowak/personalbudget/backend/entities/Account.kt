package com.pnowak.personalbudget.backend.entities

import java.math.BigDecimal
import javax.persistence.*

@Entity
class Account (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val amount: BigDecimal,
        @ManyToOne
        val user: User,
        val name: String,
        val currency: String,
        val type: Int
)
