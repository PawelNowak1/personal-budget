package com.pnowak.personalbudget.backend.entities

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
class Operation (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val name: String,
    val createDate: Date,
    val operationDate: Date,
    val description: String,
    @ManyToOne val category: Category,
    val amount: BigDecimal,
    @ManyToOne
    val account: Account
)
