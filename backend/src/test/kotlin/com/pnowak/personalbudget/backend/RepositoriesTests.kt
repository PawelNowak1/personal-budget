package com.pnowak.personalbudget.backend

import org.assertj.core.api.Assertions.assertThat
import com.pnowak.personalbudget.backend.entities.Account
import com.pnowak.personalbudget.backend.entities.Category
import com.pnowak.personalbudget.backend.entities.Operation
import com.pnowak.personalbudget.backend.entities.User
import com.pnowak.personalbudget.backend.enums.CategoryType
import com.pnowak.personalbudget.backend.repository.AccountRepository
import com.pnowak.personalbudget.backend.repository.OperationRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.math.BigDecimal
import java.util.*

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val accountRepository: AccountRepository,
        val operationRepository: OperationRepository
) {

    @Test
    fun `sumAmountByUserId equals to account sums`() {
        val user = User(id = null, username = "TestUser", active = true, email = "TestUser@gmail.com", password = "password")
        entityManager.persist(user)
        val account1 = Account(id = null, amount = BigDecimal.valueOf(3.10), user = user, name = "account1", currency = "PLN", type = 0, active = true)
        val account2 = Account(id = null, amount = BigDecimal.valueOf(13.11), user = user, name = "account2", currency = "PLN", type = 0, active = true)
        entityManager.persist(account1)
        entityManager.persist(account2)
        entityManager.flush()
        val repositorySum = accountRepository.sumAmountByUserId(user.id)
        val accountSum = account1.amount + account2.amount
        assert(repositorySum.compareTo(accountSum) == 0)
    }

    @Test
    fun `when findAllByAccountUserId return created operations`() {
        val user = User(id = null, username = "TestUser", active = true, email = "TestUser@gmail.com", password = "password")
        val account = Account(id = null, amount = BigDecimal.valueOf(3.10), user = user, name = "account1", currency = "PLN", type = 0, active = true)
        val incomeCategory = Category(id = null, user = user, parent = null, title = "Przychody", type = CategoryType.INCOME, orderNum = 1)
        val salaryCategory = Category(id = null, user = user, parent = incomeCategory, title = "Wynagrodzenie", type = null, orderNum = 1)
        val operation1 = Operation(id = null, operationDate = Date(), createDate = Date(), description = "", category = salaryCategory, account = account, amount = BigDecimal.valueOf(31))
        val operation2 = Operation(id = null, operationDate = Date(), createDate = Date(), description = "", category = salaryCategory, account = account, amount = BigDecimal.valueOf(1))
        val operation3 = Operation(id = null, operationDate = Date(), createDate = Date(), description = "", category = salaryCategory, account = account, amount = BigDecimal.valueOf(22))
        entityManager.persist(user)
        entityManager.persist(account)
        entityManager.persist(incomeCategory)
        entityManager.persist(salaryCategory)
        entityManager.persist(operation1)
        entityManager.persist(operation2)
        entityManager.persist(operation3)
        entityManager.flush()
        val found = operationRepository.findAllByAccountUserId(user.id!!)
        assertThat(found.indexOf(operation1) > -1)
        assertThat(found.indexOf(operation2) > -1)
        assertThat(found.indexOf(operation3) > -1)
    }
}
