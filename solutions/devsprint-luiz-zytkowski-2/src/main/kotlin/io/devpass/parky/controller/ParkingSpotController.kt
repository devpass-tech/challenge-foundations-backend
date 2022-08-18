package io.devpass.parky.controller

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.service.ParkingSpotService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/parking-spot")
class ParkingSpotController(
    private val parkingSpotService: ParkingSpotService,
) {
    @GetMapping
    fun findAll(): ResponseEntity<MutableIterable<ParkingSpot>> {
        val listOfParkingSpot = parkingSpotService.findAll()
        return ResponseEntity.ok(listOfParkingSpot)
    }
}