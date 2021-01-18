package com.pnowak.personalbudget.backend.controller

import com.pnowak.personalbudget.backend.dto.LoginRequest
import com.pnowak.personalbudget.backend.service.impl.AuthenticationServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@CrossOrigin(origins = ["*"], maxAge = 3600)
@Controller
@RequestMapping("/login")
class LoginController(val authenticationService: AuthenticationServiceImpl) {

    @PostMapping
    fun authenticateUser(@RequestBody loginRequest: LoginRequest?): ResponseEntity<*>? {
        return ResponseEntity.ok(authenticationService.authenticateUser(loginRequest))
    }

}
