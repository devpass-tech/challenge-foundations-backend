package io.devpass.parky.controller

import io.devpass.parky.service.ParkingSpotMovementService
import io.devpass.parky.entity.ParkingSpotMovement
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/parking-spot-movements")
class ParkingSpotMovementController(
    private val parkingSpotMovementService: ParkingSpotMovementService,
) {
    @GetMapping("/{parkingSpotId}")
    fun listAllMovement(
        @PathVariable parkingSpotId: Int,
    ): ResponseEntity<List<ParkingSpotMovement>> {
        val listOfSpotMovement = parkingSpotMovementService.listAllMovement(parkingSpotId)
        return ResponseEntity.ok(listOfSpotMovement)
    }
}