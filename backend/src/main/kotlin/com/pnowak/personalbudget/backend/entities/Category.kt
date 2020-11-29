package com.pnowak.personalbudget.backend.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.pnowak.personalbudget.backend.enums.CategoryType
import javax.persistence.*

@Entity
class Category (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "ID_PARENT")
    val parent: Category?,
    val title: String,
    @JsonIgnore
    @ManyToOne
    val user: User,
    val type: String,
    val orderNum: Int?
)
