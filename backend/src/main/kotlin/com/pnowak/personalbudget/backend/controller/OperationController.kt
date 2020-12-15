package com.pnowak.personalbudget.backend.controller

import com.pnowak.personalbudget.backend.dto.CategoryDTO
import com.pnowak.personalbudget.backend.dto.CreateOperationDTO
import com.pnowak.personalbudget.backend.dto.MyUserDetails
import com.pnowak.personalbudget.backend.entities.Operation
import com.pnowak.personalbudget.backend.service.interfaces.OperationService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/operation")
class OperationController(val operationService: OperationService) {

    fun getUserIdFromContext(): Long? {
        val authentication: MyUserDetails = SecurityContextHolder.getContext().authentication.principal as MyUserDetails
        return authentication.id
    }

    @PostMapping("/create")
    fun createOperation(@RequestBody createAccountDTO: CreateOperationDTO): ResponseEntity<Long> {
        return ResponseEntity.ok(operationService.createOperation(createAccountDTO, getUserIdFromContext()))
    }

    @PostMapping("/update")
    fun updateOperation(@RequestBody createAccountDTO: CreateOperationDTO): ResponseEntity<Long> {
        return ResponseEntity.ok(operationService.updateOperation(createAccountDTO, getUserIdFromContext()))
    }


    @GetMapping("/get")
    fun getOperations(@RequestParam(required = false) month: Int?,
                      @RequestParam(required = false) year: Int?) : ResponseEntity<List<Operation>> {
        return ResponseEntity.ok(operationService.getOperations(month, year, getUserIdFromContext()!!))
    }

    @DeleteMapping("/delete/{operationId}")
    fun deleteOperation(@PathVariable operationId: Long): ResponseEntity<Unit> {
        return ResponseEntity.ok(operationService.deleteOperation(operationId))
    }
}
