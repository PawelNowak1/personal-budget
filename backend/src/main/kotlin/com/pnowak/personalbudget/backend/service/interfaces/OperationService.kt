package com.pnowak.personalbudget.backend.service.interfaces

import com.pnowak.personalbudget.backend.dto.CreateOperationDTO
import com.pnowak.personalbudget.backend.entities.Operation

interface OperationService {
    fun createOperation(createOperationDTO: CreateOperationDTO, userId: Long?): Long
    fun getOperations(month: Int?, year: Int?, userIdFromContext: Long): List<Operation>
    fun getOperations(userIdFromContext: Long): List<Operation>
    fun deleteOperation(operationId: Long)
}
