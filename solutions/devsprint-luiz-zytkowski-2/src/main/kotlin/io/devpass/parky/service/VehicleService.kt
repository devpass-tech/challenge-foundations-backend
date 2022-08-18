package io.devpass.parky.service

import io.devpass.parky.entity.Vehicle
import io.devpass.parky.repository.VehicleRepository
import org.springframework.stereotype.Service

@Service
class VehicleService(
    private val vehicleRepository: VehicleRepository,
) {
    fun findById(vehicleId: Int): Vehicle?{
        return vehicleRepository.findById(vehicleId).let {
            if(it.isPresent) it.get() else null
        }
    }
}