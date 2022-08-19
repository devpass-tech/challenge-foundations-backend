package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository,
) {
    fun findById(parkingSpotId: Int): ParkingSpot? {
        return parkingSpotRepository.findById(parkingSpotId).let {
            if (it.isPresent) it.get() else null
        }
    }

    fun findEmptyParkingSpotAtRandom(): ParkingSpot{
        return parkingSpotRepository.getRandomEmptyParkingSpot().random()
    }
}