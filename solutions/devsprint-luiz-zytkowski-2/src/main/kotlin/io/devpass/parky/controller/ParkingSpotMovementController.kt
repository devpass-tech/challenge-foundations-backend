package io.devpass.parky.controller

import io.devpass.parky.service.ParkingSpotMovementService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/parking-spots")
class ParkingSpotMovementController(
    private val parkingSpotMovementService: ParkingSpotMovementService) {}