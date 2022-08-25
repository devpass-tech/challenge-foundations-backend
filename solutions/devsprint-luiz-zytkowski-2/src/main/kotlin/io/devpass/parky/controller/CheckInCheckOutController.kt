package io.devpass.parky.controller

import io.devpass.parky.requests.CheckInRequest
import io.devpass.parky.service.CheckInOutService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping("/check-in-out")
class CheckInCheckOutController(
    private val checkInOutService: CheckInOutService,
) {

    @PostMapping
    fun createCheckIn(
        @RequestBody checkInRequest: CheckInRequest
    ): ResponseEntity<String> {
        checkInOutService.createCheckIn(checkInRequest)
        return ResponseEntity.ok("Deu bom")
    }

    @DeleteMapping("/{parkingSpotId}")
    fun checkOut(@PathVariable parkingSpotId: Int): HttpStatus {
        checkInOutService.checkOut(parkingSpotId)
        return HttpStatus.OK
    }
}