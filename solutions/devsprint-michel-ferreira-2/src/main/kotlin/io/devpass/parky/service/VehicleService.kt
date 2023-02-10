package io.devpass.parky.service

import io.devpass.parky.entity.Vehicle
import io.devpass.parky.repository.VehicleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VehicleService(
    private val vehicleRepository: VehicleRepository
) {

    fun findAll(): List<Vehicle> {
        return vehicleRepository.findAll().toList()
    }

    fun findById(vehicleId: String): Optional<Vehicle> {
        return vehicleRepository.findById(vehicleId)
    }

    fun create(vehicle: Vehicle): Vehicle {
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
}