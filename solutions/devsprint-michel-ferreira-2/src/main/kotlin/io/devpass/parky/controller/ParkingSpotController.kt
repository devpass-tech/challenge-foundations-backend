package io.devpass.parky.controller

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.service.ParkingSpotService
import io.devpass.parky.service.model.ParkingSpotFilter
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/listParkingSpot")
class ParkingSpotController(
    private val parkingSpotService: ParkingSpotService
) {
    @GetMapping
    fun listParkingSpot(
        @RequestParam(name = "floor", required = false) floor: Int?,
        @RequestParam(name = "spot", required = false) spot: Int?,
        @RequestParam(name = "inUse", required = false) inUse: Boolean?
    ): ResponseEntity<List<ParkingSpot>> {
        val parkingSport = parkingSpotService.listParkingSpot(
            ParkingSpotFilter(floor, spot, inUse)
        )

        return ResponseEntity.ok(parkingSport)
    }
}