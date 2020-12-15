package com.pnowak.personalbudget.backend.service.impl

import com.pnowak.personalbudget.backend.dto.JwtResponse
import com.pnowak.personalbudget.backend.dto.LoginRequest
import com.pnowak.personalbudget.backend.dto.MyUserDetails
import com.pnowak.personalbudget.backend.dto.RegistrationDTO
import com.pnowak.personalbudget.backend.entities.Category
import com.pnowak.personalbudget.backend.entities.User
import com.pnowak.personalbudget.backend.enums.CategoryType
import com.pnowak.personalbudget.backend.exception.UserAlreadyExistsException
import com.pnowak.personalbudget.backend.repository.CategoryRepository
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
                                private val categoryRepository: CategoryRepository,
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
        generateUserCategories(user)
        val token = jwtUtils.generateJwtToken(registrationDTO.username)
        return JwtResponse(
                token,
                user.id,
                user.username,
                user.email
        )
    }


    // need change
    fun generateUserCategories(user: User) {
        val incomeCategory = categoryRepository.save(Category(id = null, user = user, parent = null, title = "Przychody", type = CategoryType.INCOME, orderNum = 1))
        val billsCategory = categoryRepository.save(Category(id = null, user = user, parent = null, title = "Rachunki", type = CategoryType.EXPENSE, orderNum = 2))
        val transportCategory = categoryRepository.save(Category(id = null, user = user, parent = null, title = "Transport", type = CategoryType.EXPENSE, orderNum = 3))
        val foodCategory = categoryRepository.save(Category(id = null, user = user, parent = null, title = "Jedzenie", type = CategoryType.EXPENSE, orderNum = 4))
        val entertainmentCategory = categoryRepository.save(Category(id = null, user = user, parent = null, title = "Rozrywka", type = CategoryType.EXPENSE, orderNum = 5))
        val sportCategory = categoryRepository.save(Category(id = null, user = user, parent = null, title = "Sport", type = CategoryType.EXPENSE, orderNum = 6))

        categoryRepository.save(Category(id = null, user = user, parent = incomeCategory, title = "Wynagrodzenie", type = null, orderNum = 1))
        categoryRepository.save(Category(id = null, user = user, parent = incomeCategory, title = "Premia", type = null, orderNum = 2))
        categoryRepository.save(Category(id = null, user = user, parent = billsCategory, title = "Czynsz", type = null, orderNum = 3))
        categoryRepository.save(Category(id = null, user = user, parent = billsCategory, title = "Media", type = null, orderNum = 4))
        categoryRepository.save(Category(id = null, user = user, parent = transportCategory, title = "Bilety", type = null, orderNum = 5))
        categoryRepository.save(Category(id = null, user = user, parent = transportCategory, title = "Uber", type = null, orderNum = 6))
        categoryRepository.save(Category(id = null, user = user, parent = foodCategory, title = "Zakupy", type = null, orderNum = 7))
        categoryRepository.save(Category(id = null, user = user, parent = foodCategory, title = "Na mieście", type = null, orderNum = 8))
        categoryRepository.save(Category(id = null, user = user, parent = entertainmentCategory, title = "Gry komputerowe", type = null, orderNum = 9))
        categoryRepository.save(Category(id = null, user = user, parent = entertainmentCategory, title = "Kino", type = null, orderNum = 10))
        categoryRepository.save(Category(id = null, user = user, parent = entertainmentCategory, title = "Netflix", type = null, orderNum = 11))
        categoryRepository.save(Category(id = null, user = user, parent = sportCategory, title = "Karnet", type = null, orderNum = 12))
        categoryRepository.save(Category(id = null, user = user, parent = sportCategory, title = "Ubrania", type = null, orderNum = 13))
    }
}
