package io.devpass.parky.controller

import io.devpass.parky.controller.request.VehicleRequest
import io.devpass.parky.entity.Vehicle
import io.devpass.parky.framework.getOrNull
import io.devpass.parky.requests.CheckInRequest
import io.devpass.parky.service.CheckInOutService
import io.devpass.parky.service.ParkingSpotEventService
import io.devpass.parky.service.ParkingSpotService
import io.devpass.parky.service.VehicleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/tests")
class TestController(
    private val checkInOutService: CheckInOutService,
    private val parkingSpotService: ParkingSpotService,
    private val vehicleService: VehicleService
) {

    @GetMapping("/all-vehicles")
    fun getAllVehicles(): List<Vehicle> {
        return listOf<Vehicle>(
            Vehicle(UUID.randomUUID().toString(), "Honda", "Branco", "Michel", "PWL-7717")
        )
    }

    @GetMapping("/spot-by-id/{id}")
    fun getParkingSpotById(
        @PathVariable id: Int
    ) = parkingSpotService.findById(id)
    @GetMapping("/vehicles/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findVehiclesById(@PathVariable id: String): Vehicle? {
        return vehicleService.findById(id).getOrNull()
    }

    @GetMapping("/vehicles")
    @ResponseStatus(HttpStatus.OK)
    fun findVehiclesById(): List<Vehicle> {
        return vehicleService.findAll()
    }

    @PostMapping("/vehicles")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: VehicleRequest) {
        val vehicle = Vehicle(
            //necessarios no request
            brand = request.brand,
            color = request.color,
            owner = request.owner,
            licensePlate = request.licensePlate
        )
        vehicleService.create(vehicle)
    }
    @PostMapping("/check-in")
    @ResponseStatus(HttpStatus.CREATED)
    fun checkIn(@RequestBody request: CheckInRequest) {
        checkInOutService.checkIn(request)
    }
}