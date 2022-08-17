package io.devpass.parky.controller

import io.devpass.parky.service.CheckInOutService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/check-in-out")
class CheckInCheckOutController (
    private val checkInOutService : CheckInOutService
        ) {
}