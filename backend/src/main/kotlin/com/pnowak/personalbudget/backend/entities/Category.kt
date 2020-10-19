package com.pnowak.personalbudget.backend.entities

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
    @ManyToOne
    val user: User
)
