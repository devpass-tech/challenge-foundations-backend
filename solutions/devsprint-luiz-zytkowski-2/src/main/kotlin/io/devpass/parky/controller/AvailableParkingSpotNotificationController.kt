package io.devpass.parky.controller

import io.devpass.parky.service.AvailableParkingSpotNotificationService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/available-parking-spot")
class AvailableParkingSpotNotificationController(
    private val availableParkingSpotNotificationService: AvailableParkingSpotNotificationService,
)