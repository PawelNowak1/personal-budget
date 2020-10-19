package com.pnowak.personalbudget.backend.entities

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
class BudgetPlan (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val amount: BigDecimal,
    val monthYear: Date,
    @ManyToOne
    val category: Category,
    @ManyToOne
    val user: User
)
