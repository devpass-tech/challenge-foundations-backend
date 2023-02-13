package io.devpass.parky.controller

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.service.ParkingSpotService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping()
class ParkingSpotController (
    private val parkingSpotService: ParkingSpotService
){
    @GetMapping("/listParkingSpot")
    fun listParkingSpot(
        @PathVariable id: Long,
        @RequestParam(required = false) floor: Int,
        @RequestParam(required = false) spot: Int,
        @RequestParam( name = "inUse", required = false) InUseBy: Boolean?): List<ParkingSpot>{
        return parkingSpotService.listParkingSpotService()
    }
}