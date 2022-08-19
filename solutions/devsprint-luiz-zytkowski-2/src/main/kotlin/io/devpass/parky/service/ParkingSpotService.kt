package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository,
) {
    fun findAll() : List<ParkingSpot>{
        return parkingSpotRepository.findAll().toList()
    }
}
