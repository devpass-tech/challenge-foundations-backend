package io.devpass.parky.controller

import io.devpass.parky.controller.response.VehicleEventsResponse
import io.devpass.parky.service.ParkingSpotEventService
import io.devpass.parky.service.VehicleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/vehicle")
class VehicleController(
    private val vehicleService: VehicleService,
    private val parkingSpotEventService: ParkingSpotEventService
) {
    @GetMapping("/{vehicleId}")
    fun getVehicleById(
        @PathVariable vehicleId: String
    ): ResponseEntity<VehicleEventsResponse> {
        val vehicle = vehicleService.findById(vehicleId)
        return if (vehicle != null) {
            val events = parkingSpotEventService.findByVehicleId(vehicleId)
            ResponseEntity.ok(VehicleEventsResponse(vehicle = vehicle, history = events))
        } else {
            ResponseEntity.notFound().build()
        }
    }
}