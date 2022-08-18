package io.devpass.parky.controller

import io.devpass.parky.entity.Vehicle
import io.devpass.parky.service.VehicleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vehicles")
class VehicleController(
    private val vehicleService: VehicleService,
) {
    @GetMapping("/{vehicleId}")
    fun getVehicleById(
        @PathVariable vehicleId: Int,
    ): ResponseEntity<Vehicle> {
        val vehicle = vehicleService.findById(vehicleId)
        return if (vehicle != null) {
            ResponseEntity.ok(vehicle)
        } else ResponseEntity.notFound().build()
    }
}