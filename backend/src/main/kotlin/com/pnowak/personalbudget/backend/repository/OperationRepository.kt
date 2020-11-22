package com.pnowak.personalbudget.backend.repository

import com.pnowak.personalbudget.backend.entities.Account
import com.pnowak.personalbudget.backend.entities.Operation
import org.springframework.data.repository.CrudRepository

interface OperationRepository : CrudRepository<Operation, Long> {
}
