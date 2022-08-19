package io.devpass.parky.controller

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.repository.ParkingSpotRepository
import io.devpass.parky.service.ParkingSpotService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/parking-spot")
class ParkingSpotController(
    private val parkingSpotService: ParkingSpotService,
){
    @GetMapping("/parking-spot-random-vacancy")
    fun getRandomEmptyParkingSpot(): ParkingSpot {
        return parkingSpotService.findEmptyParkingSpotAtRandom()
    }
}