package com.pnowak.personalbudget.backend.controller

import com.pnowak.personalbudget.backend.dto.JwtResponse
import com.pnowak.personalbudget.backend.dto.LoginRequest
import com.pnowak.personalbudget.backend.dto.MyUserDetails
import com.pnowak.personalbudget.backend.repository.UserRepository
import com.pnowak.personalbudget.backend.security.JwtUtils
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

@CrossOrigin(origins = ["*"], maxAge = 3600)
@Controller
@RequestMapping("/login")
class LoginController(val authenticationManager: AuthenticationManager,
                      val jwtUtils: JwtUtils) {

    @PostMapping
    open fun authenticateUser(@RequestBody loginRequest: LoginRequest?): ResponseEntity<*>? {
        var authentication: Authentication? = null
        if (loginRequest != null) {
            authentication = try {
                authenticationManager.authenticate(
                        UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password))
            } catch (e: AuthenticationException) {
                return ResponseEntity.badRequest()
                        .body("Authentication error: " + e.message + "\n")
            }

            SecurityContextHolder.getContext().authentication = authentication
            val jwt: String = jwtUtils.generateJwtToken(authentication)
            val userDetails = authentication.principal as MyUserDetails

            return ResponseEntity.ok<Any>(JwtResponse(
                    jwt,
                    userDetails.id,
                    userDetails.username,
                    userDetails.email
            ))
        } else {
            return ResponseEntity.badRequest()
                    .body("Provide username and password")
        }
    }

}
