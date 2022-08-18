package io.devpass.parky.controller

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.service.ParkingSpotService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/parking-spot")
class ParkingSpotController(
    private val parkingSpotService: ParkingSpotService,
)

@GetMapping("/{parkingSpotId}")
fun getParkingSpotById(
    @PathVariable parkingSpotId: Int
): ResponseEntity<ParkingSpot> {
    val parkingSpot = ParkingSpotService.findById(parkingSpotId)
    return if (parkingSpot != null) {
        ResponseEntity.ok(parkingSpot)
    } else ResponseEntity.notFound().build()
}