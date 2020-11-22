package com.pnowak.personalbudget.backend.service.interfaces

import com.pnowak.personalbudget.backend.dto.CreateAccountDTO
import com.pnowak.personalbudget.backend.dto.CreateOperationDTO
import com.pnowak.personalbudget.backend.entities.Account

interface OperationService {
    fun createOperation(createOperationDTO: CreateOperationDTO, userId: Long?): Long
}
