package com.pnowak.personalbudget.backend.service.interfaces

import com.pnowak.personalbudget.backend.dto.JwtResponse
import com.pnowak.personalbudget.backend.dto.LoginRequest
import com.pnowak.personalbudget.backend.dto.RegistrationDTO

interface AuthenticationService {
    fun authenticateUser(loginRequest: LoginRequest?): JwtResponse
    fun registrateUser(registrationDTO: RegistrationDTO): JwtResponse
}
