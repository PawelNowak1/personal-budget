package com.pnowak.personalbudget.backend.service.impl

import com.pnowak.personalbudget.backend.dto.JwtResponse
import com.pnowak.personalbudget.backend.dto.LoginRequest
import com.pnowak.personalbudget.backend.dto.MyUserDetails
import com.pnowak.personalbudget.backend.dto.RegistrationDTO
import com.pnowak.personalbudget.backend.entities.User
import com.pnowak.personalbudget.backend.exception.UserAlreadyExistsException
import com.pnowak.personalbudget.backend.repository.UserRepository
import com.pnowak.personalbudget.backend.security.JwtUtils
import com.pnowak.personalbudget.backend.service.interfaces.AuthenticationService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(private val authenticationManager: AuthenticationManager,
                                private val jwtUtils: JwtUtils,
                                private val userRepository: UserRepository,
                                private val encoder: PasswordEncoder) : AuthenticationService {

    override fun authenticateUser(loginRequest: LoginRequest?): JwtResponse {
        val authentication: Authentication?
        authentication = try {
            authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken(loginRequest?.username, loginRequest?.password))
        } catch (e: AuthenticationException) {
            throw e
        }

        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtUtils.generateJwtToken(authentication)
        val userDetails = authentication.principal as MyUserDetails

        return JwtResponse(
                jwt,
                userDetails.id,
                userDetails.username,
                userDetails.email
        )
    }

    override fun registrateUser(registrationDTO: RegistrationDTO): JwtResponse {
        if (userRepository.existsByUsername(registrationDTO.username) ||
                userRepository.existsByEmail(registrationDTO.email)) {
            throw UserAlreadyExistsException("User with specified username or email already exists")
        }

        val user = User(null, registrationDTO.username, true, registrationDTO.email, encoder.encode(registrationDTO.password))
        userRepository.save(user)
        val token = jwtUtils.generateJwtToken(registrationDTO.username)
        return JwtResponse(
                token,
                user.id,
                user.username,
                user.email
        )
    }


}
