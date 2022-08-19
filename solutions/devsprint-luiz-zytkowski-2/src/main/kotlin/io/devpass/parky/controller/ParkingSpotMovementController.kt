package io.devpass.parky.controller

import io.devpass.parky.service.ParkingSpotMovementService
import io.devpass.parky.service.VehicleService
import io.devpass.parky.service.entity.ParkingSpot
import io.devpass.parky.service.entity.ParkingSpotMovement
import io.devpass.parky.service.entity.Vehicle
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/parking-spot-movements")
class ParkingSpotMovementController(
        private val parkingSpotMovementService: ParkingSpotMovementService,
) {
    @GetMapping("/{parkingSpotId}")
    fun listAllMovement(
            @PathVariable parkingSpotId: Int
    ): ResponseEntity<List<ParkingSpotMovement>> {
        val listOfSpotMovement = parkingSpotMovementService.listAllMovement(parkingSpotId)
        return ResponseEntity.ok(listOfSpotMovement)
    }
}