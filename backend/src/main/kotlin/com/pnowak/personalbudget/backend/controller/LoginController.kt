package com.pnowak.personalbudget.backend.controller

import com.pnowak.personalbudget.backend.dto.JwtResponse
import com.pnowak.personalbudget.backend.dto.LoginRequest
import com.pnowak.personalbudget.backend.dto.MyUserDetails
import com.pnowak.personalbudget.backend.repository.UserRepository
import com.pnowak.personalbudget.backend.security.JwtUtils
import com.pnowak.personalbudget.backend.service.impl.AuthenticationServiceImpl
import com.pnowak.personalbudget.backend.service.interfaces.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import kotlin.math.log

@CrossOrigin(origins = ["*"], maxAge = 3600)
@Controller
@RequestMapping("/login")
class LoginController(val authenticationService: AuthenticationServiceImpl) {

    @PostMapping
    open fun authenticateUser(@RequestBody loginRequest: LoginRequest?): ResponseEntity<*>? {
        return ResponseEntity.ok(authenticationService.authenticateUser(loginRequest))
    }

}
