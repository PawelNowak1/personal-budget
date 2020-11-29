package com.pnowak.personalbudget.backend.repository

import com.pnowak.personalbudget.backend.entities.Operation
import org.springframework.data.repository.CrudRepository
import java.util.*

interface OperationRepository : CrudRepository<Operation, Long> {
    fun findAllByOperationDateGreaterThanEqualAndOperationDateLessThanEqualAndAccountUserIdOrderByOperationDate(dateStart: Date, dateEnd: Date, userId: Long): List<Operation>
    fun findAllByAccountUserId(userId: Long): List<Operation>
}
