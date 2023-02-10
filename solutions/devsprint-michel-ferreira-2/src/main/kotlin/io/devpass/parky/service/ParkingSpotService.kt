package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.entity.Vehicle
import io.devpass.parky.framework.getOrNull
import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.stereotype.Service
@Suppress("UNCHECKED_CAST")
@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository
) {
    fun findById(id: Int): ParkingSpot? {
        return parkingSpotRepository.findById(id).getOrNull()
    }

    fun findAllParkingSpot(): List<ParkingSpot> {
        return parkingSpotRepository.findAll() as List<ParkingSpot>
    }
}