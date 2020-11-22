package com.pnowak.personalbudget.backend.entities

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity
class Operation(

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,

        val name: String = "", // do usunięcia
        var createDate: Date = Date(),
        var operationDate: Date = Date(),
        var description: String? = null,
        @ManyToOne var category: Category? = null,
        var amount: BigDecimal = BigDecimal.ZERO,
        @ManyToOne
        var account: Account = Account()
)
