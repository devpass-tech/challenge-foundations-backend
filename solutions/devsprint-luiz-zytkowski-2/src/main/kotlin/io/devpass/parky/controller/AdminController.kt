package io.devpass.parky.controller

import io.devpass.parky.service.CheckInOutService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class AdminController(
    private val checkInOutService: CheckInOutService
) {
    @PostMapping("/free-all-parking-spots")
    fun freeAllParkingSpots(): HttpStatus {
        checkInOutService.cleanAllParkingSpots()
        return HttpStatus.OK
    }
}