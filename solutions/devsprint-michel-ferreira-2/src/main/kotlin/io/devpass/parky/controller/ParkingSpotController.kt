package io.devpass.parky.controller

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.service.ParkingSpotService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/listParkingSpot")
class ParkingSpotController(
    private val parkingSpotService: ParkingSpotService
) {
    @GetMapping()
    fun listParkingSpot(
        @PathVariable id: Int,
        @RequestParam(required = false) floor: Int,
        @RequestParam(required = false) spot: Int,
        @RequestParam(required = true) InUseBy: String
    ): ResponseEntity<ParkingSpot> {
        val parkingSport = parkingSpotService.listParkingSpot("inUse")

        return ResponseEntity.ok(parkingSport)
    }
}