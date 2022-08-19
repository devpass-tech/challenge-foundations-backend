package io.devpass.parky.controller

import io.devpass.parky.requests.CheckInRequest
import io.devpass.parky.service.CheckInOutService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/check-in-out")
class CheckInCheckOutController(
        private val checkInOutService: CheckInOutService,
) {
    @PostMapping
    fun createCheckIn(
            @RequestBody checkInRequest: CheckInRequest
    ): ResponseEntity<String>{
        checkInOutService.createCheckIn(checkInRequest)
        return ResponseEntity.ok("Deu bom")
    }
}