package com.pnowak.personalbudget.backend

import com.ninjasquad.springmockk.MockkBean
import com.pnowak.personalbudget.backend.dto.MyUserDetails
import com.pnowak.personalbudget.backend.entities.Account
import com.pnowak.personalbudget.backend.entities.User
import com.pnowak.personalbudget.backend.repository.*
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.math.BigDecimal


@WebMvcTest
@ComponentScan(basePackages = ["com.pnowak.personalbudget.backend"])
class HttpControllersTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var accountRepository: AccountRepository
    @MockkBean
    lateinit var userRepository: UserRepository
    @MockkBean
    lateinit var categoryRepository: CategoryRepository
    @MockkBean
    lateinit var operationRepository: OperationRepository
    @MockkBean
    lateinit var budgetRepository: BudgetRepository
    @MockkBean
    lateinit var namedParameterJdbcTemplate: NamedParameterJdbcTemplate

    @WithMockUser(authorities = ["ADMIN"])
    @Test
    fun `List user accounts`() {
        val user = User(id = null, username = "TestUser", active = true, email = "TestUser@gmail.com", password = "password")
        val account1 = Account(id = null, amount = BigDecimal.valueOf(3.10), user = user, name = "account1", currency = "PLN", type = 0, active = true)
        val account2 = Account(id = null, amount = BigDecimal.valueOf(3.10), user = user, name = "account2", currency = "PLN", type = 1, active = true)

        every { accountRepository.findAllByUserId(1L) } returns listOf(account1, account2)
        mockMvc.perform(get("/account/list")
                .with(user(MyUserDetails(id = 1, username = "test", email = "test", password = "test", active = true, authority = SimpleGrantedAuthority("ROLE_USER"))))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].name").value(account1.name))
                .andExpect(jsonPath("\$.[1].name").value(account2.name))
                .andExpect(jsonPath("\$.[0].currency").value(account1.currency))
                .andExpect(jsonPath("\$.[1].currency").value(account2.currency))
    }

    @WithMockUser(authorities = ["ADMIN"])
    @Test
    fun `Not found request`() {
        mockMvc.perform(post("/notFoundUrl")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `Unauthorized request`() {
        mockMvc.perform(get("/account/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized)
    }

    @WithMockUser(authorities = ["ADMIN"])
    @Test
    fun `Bad request`() {
        mockMvc.perform(post("/account/create")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest)
    }
}
