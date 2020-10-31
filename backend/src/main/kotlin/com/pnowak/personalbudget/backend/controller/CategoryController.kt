package com.pnowak.personalbudget.backend.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/categories")
class CategoryController {

    @GetMapping("/abcdef")
    fun getCategories(): String {
        return "dasdas"
    }
}
