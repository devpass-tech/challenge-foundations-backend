package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository
) {
    fun listParkingSpotService(): List<ParkingSpot> {
        val inUseBy = null
        if (inUseBy != null) {
            parkingSpotRepository.findAll().toList()
        } else if (inUseBy == false) {
            parkingSpotRepository.findByInUse()
        } else {
            parkingSpotRepository.findByInUseByIsNull()
        }

        return parkingSpotRepository.findByInUseByIsNull()
    }
}


