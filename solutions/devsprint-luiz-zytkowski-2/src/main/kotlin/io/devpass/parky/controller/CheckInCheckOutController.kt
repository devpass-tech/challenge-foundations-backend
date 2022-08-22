package io.devpass.parky.controller

import io.devpass.parky.service.CheckInOutService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/check-in-out")
class CheckInCheckOutController(
    private val checkInOutService: CheckInOutService,
) {
    @DeleteMapping("/{parkingSpotId}")
    fun deleteCheckIn(@PathVariable parkingSpotId: Int): HttpStatus {
        checkInOutService.removeCheckIn(parkingSpotId)
        return HttpStatus.OK
    }
}