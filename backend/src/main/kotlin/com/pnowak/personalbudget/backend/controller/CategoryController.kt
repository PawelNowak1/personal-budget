package com.pnowak.personalbudget.backend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class CategoryController {

    @GetMapping("/abcdef")
    fun getCategories(): String {
        return "dasdas"
    }
}
