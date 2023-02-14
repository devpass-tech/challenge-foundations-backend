package io.devpass.parky.controller

import io.devpass.parky.requests.CheckInRequest
import io.devpass.parky.requests.CheckOutRequest
import io.devpass.parky.service.CheckInOutService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/check")
class CheckInOutController(
    private val checkInOutService: CheckInOutService
) {
    @PostMapping("/in")
    @ResponseStatus(HttpStatus.CREATED)
    fun checkIn(@RequestBody request: CheckInRequest) =
        ResponseEntity.status(HttpStatus.CREATED).body(checkInOutService.checkIn(request))

    @PostMapping("/out")
    @ResponseStatus(HttpStatus.CREATED)
    fun checkOut(@RequestBody request: CheckOutRequest) = checkInOutService.checkOut(request)
}