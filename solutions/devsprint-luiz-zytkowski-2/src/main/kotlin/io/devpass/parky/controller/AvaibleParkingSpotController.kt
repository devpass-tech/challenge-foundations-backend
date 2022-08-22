package io.devpass.parky.controller

import io.devpass.parky.entity.AvaibleParkingSpot
import io.devpass.parky.service.AvaibleParkingSpotService
import io.devpass.parky.service.ParkingSpotService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/avaible-parking-spot")
class AvaibleParkingSpotController(
    private val avaibleParkingSpotService: AvaibleParkingSpotService
)