package com.pnowak.personalbudget.backend.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import java.math.BigDecimal
import javax.persistence.*

@Entity
class Account (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        var amount: BigDecimal = BigDecimal.ZERO,
        @JsonIgnore
        @ManyToOne
        var user: User? = null,
        var name: String = "",
        var currency: String = "",
        var type: Int = 0,
        var active: Boolean = true
)
