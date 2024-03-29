package io.devpass.parky.service

import io.devpass.parky.controller.response.VehicleEventsResponse
import io.devpass.parky.entity.Vehicle
import io.devpass.parky.framework.NotFoundException
import io.devpass.parky.framework.VehicleException
import io.devpass.parky.framework.getOrNull
import io.devpass.parky.repository.VehicleRepository
import org.springframework.stereotype.Service

@Service
class VehicleService(
    private val vehicleRepository: VehicleRepository,
    private val parkingSpotEventService: ParkingSpotEventService
) {

    fun findAll(): List<Vehicle> {
        return vehicleRepository.findAll().toList()
    }

    fun findById(vehicleId: String): Vehicle? {
        return vehicleRepository.findById(vehicleId).getOrNull()
    }

    fun create(vehicle: Vehicle) : Vehicle{
        if (findVehicleLicensePlate(vehicle.licensePlate) != null){
            throw VehicleException("This Vehicle is already insert in our Database. (license plate duplicated)")
        }
        return vehicleRepository.save(vehicle)
    }

    fun createIfNotExists(vehicle: Vehicle): Vehicle {
        vehicleRepository.findByLicensePlate(vehicle.licensePlate)?.let { return it }
        return create(
            Vehicle(
                licensePlate = vehicle.licensePlate,
                brand = vehicle.brand,
                color = vehicle.color,
                owner = vehicle.owner
            )
        )
    }

    fun findVehicleLicensePlate(licensePlate : String): Vehicle? {
        return vehicleRepository.findByLicensePlate(licensePlate)
    }

    fun findVehicleAndHistory(vehicleId: String): VehicleEventsResponse {
        vehicleRepository.findById(vehicleId).getOrNull()?.let {
            val events = parkingSpotEventService.findByVehicleId(vehicleId)
            return VehicleEventsResponse(vehicle = it, history = events)
        }
        throw NotFoundException("Vehicle not found")
    }
}