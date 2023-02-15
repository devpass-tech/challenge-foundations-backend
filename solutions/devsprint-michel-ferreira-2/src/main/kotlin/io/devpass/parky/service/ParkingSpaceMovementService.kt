package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.framework.ParkingSpotNotFoundException
import io.devpass.parky.repository.ParkingSpaceMovementRepository
import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpaceMovementService(
    private val parkingSpaceMovementRepository: ParkingSpaceMovementRepository,
    private val parkingSpotRepository: ParkingSpotRepository
) {
    fun listParkingSpaceMovement(id: Int): List<ParkingSpot>? {
        val vacancy = parkingSpotRepository.findById(id)
        return vacancy.orElseThrow { ParkingSpotNotFoundException("Parking Spot Not Found") }.let {
            parkingSpaceMovementRepository.findByParkingSpot(it.id)
        }
    }
}
