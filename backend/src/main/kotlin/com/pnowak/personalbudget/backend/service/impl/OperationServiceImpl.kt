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

}
