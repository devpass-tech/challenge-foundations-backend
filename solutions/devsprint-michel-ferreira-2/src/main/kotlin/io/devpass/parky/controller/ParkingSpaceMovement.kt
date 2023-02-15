package io.devpass.parky.controller

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.entity.ParkingSpotEvent
import io.devpass.parky.service.ParkingSpaceMovementService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/parkingSpaceMovement")
class ParkingSpaceMovement(
    private val parkingSpaceMovementService: ParkingSpaceMovementService
) {

    @GetMapping
    fun listParkingSpaceMovement(@PathVariable id: Int): ResponseEntity<List<ParkingSpotEvent>> {
        val movement = parkingSpaceMovementService.listParkingSpaceMovement(id)
        return ResponseEntity.ok(movement)
    }
}