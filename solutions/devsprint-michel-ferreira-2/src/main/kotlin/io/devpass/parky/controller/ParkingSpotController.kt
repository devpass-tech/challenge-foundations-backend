package io.devpass.parky.controller

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.service.ParkingSpotService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping()
class ParkingSpotController (
    private val parkingSpotService: ParkingSpotService
){
    @GetMapping("/listParkingSpot")
    fun listParkingSpot(
        @PathVariable id: Long,
        @RequestParam(required = true) floor: Int,
        @RequestParam(required = true) spot: Int,
        @RequestParam(required = false) InUse: Boolean?): ResponseEntity<List<ParkingSpot>> {
        val parkingSport = parkingSpotService.listParkingSpot()

        return ResponseEntity.ok(parkingSport)
    }
}