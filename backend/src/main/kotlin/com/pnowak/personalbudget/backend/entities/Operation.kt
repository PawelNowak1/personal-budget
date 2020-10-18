package com.pnowak.personalbudget.backend.entities

import javax.persistence.*

@Entity
class Operation (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val name: String
)
