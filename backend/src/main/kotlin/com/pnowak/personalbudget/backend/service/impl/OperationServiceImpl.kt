package com.pnowak.personalbudget.backend.service.impl

import com.pnowak.personalbudget.backend.dto.CreateOperationDTO
import com.pnowak.personalbudget.backend.entities.Account
import com.pnowak.personalbudget.backend.entities.Operation
import com.pnowak.personalbudget.backend.repository.AccountRepository
import com.pnowak.personalbudget.backend.repository.CategoryRepository
import com.pnowak.personalbudget.backend.repository.OperationRepository
import com.pnowak.personalbudget.backend.repository.UserRepository
import com.pnowak.personalbudget.backend.service.interfaces.AccountService
import com.pnowak.personalbudget.backend.service.interfaces.OperationService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class OperationServiceImpl (private val accountRepository: AccountRepository,
                            private val operationRepository: OperationRepository,
                            private val categoryRepository: CategoryRepository) : OperationService {
    override fun createOperation(createOperationDTO: CreateOperationDTO, userId: Long?): Long {
        val account = accountRepository.findById(createOperationDTO.accountId)
        val category = categoryRepository.findById(createOperationDTO.categoryId)
        val operation = Operation()
        operation.account = account.get()
        operation.operationDate = createOperationDTO.operationDate
        operation.amount = createOperationDTO.amount
        operation.description = createOperationDTO.description
        operation.category = category.get()
        operation.createDate = Date()
        return operationRepository.save(operation).id
    }

    override fun getOperations(month: Int?, year: Int?, userIdFromContext: Long): List<Operation> {
        if (month == null && year == null) {
            return operationRepository.findAllByAccountUserId(userIdFromContext)
        } else if (month != null && year != null) {
            val startDate = LocalDate.of(year, month, 1)
            val nextMonth = startDate.plusMonths(1).month
            val nextYear = startDate.plusMonths(1).year
            val endData = LocalDate.of(nextYear, nextMonth, 1)
            return operationRepository.findAllByOperationDateGreaterThanEqualAndOperationDateLessThanEqualAndAccountUserIdOrderByOperationDate(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endData), userIdFromContext)
        }
        return emptyList()
    }

    override fun getOperations(userIdFromContext: Long): List<Operation> {
        return operationRepository.findAllByAccountUserId(userIdFromContext)
    }
}
