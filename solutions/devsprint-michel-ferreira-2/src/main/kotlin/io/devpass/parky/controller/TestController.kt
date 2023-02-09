package io.devpass.parky.controller

import io.devpass.parky.entity.Vehicle
import io.devpass.parky.service.ParkingSpotEventService
import io.devpass.parky.service.ParkingSpotService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tests")
class TestController(
    private val parkingSpotEventService: ParkingSpotEventService,
    private val parkingSpotService: ParkingSpotService,
    private val vehSpotService: ParkingSpotService
) {

    @GetMapping("/all-vehicles")
    fun getAllVehicles() = listOf<Vehicle>(
        Vehicle(1, "Honda", "Branco", "Michel")
    )

    @GetMapping("/spot-by-id/{id}")
    fun getParkingSpotById(
        @PathVariable id: Int
    ) = parkingSpotService.findById(id)
}