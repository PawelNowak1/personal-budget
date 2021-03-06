package com.pnowak.personalbudget.backend.controller

import com.pnowak.personalbudget.backend.dto.RegistrationDTO
import com.pnowak.personalbudget.backend.exception.UserAlreadyExistsException
import com.pnowak.personalbudget.backend.service.impl.AuthenticationServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@CrossOrigin(origins = ["*"], maxAge = 3600)
@Controller
@RequestMapping("/registration")
class RegistrationController(val authenticationService: AuthenticationServiceImpl) {

    @PostMapping
    fun register(@RequestBody registrationDTO: RegistrationDTO): ResponseEntity<*>? {
        return try {
            ResponseEntity.ok(authenticationService.registrateUser(registrationDTO))
        } catch (e: UserAlreadyExistsException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }
}
