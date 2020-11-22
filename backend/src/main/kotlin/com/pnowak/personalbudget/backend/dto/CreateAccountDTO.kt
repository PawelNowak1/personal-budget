package com.pnowak.personalbudget.backend.dto

import java.math.BigDecimal

class CreateAccountDTO {
    lateinit var amount: BigDecimal
    lateinit var name: String
    lateinit var currency: String
    var type: Int = 0
    var active: Boolean = false
}
