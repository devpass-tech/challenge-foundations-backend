package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpotMovement
import io.devpass.parky.repository.ParkingSpotMovementRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotMovementService(
    private val parkingSpotMovementRepository: ParkingSpotMovementRepository,
) {
    fun listAllMovement(parkingSpotId: Int): List<ParkingSpotMovement> {
        return parkingSpotMovementRepository.listAllMovement(parkingSpotId)
    }

    fun create(parkingSpotMovement: ParkingSpotMovement) {
        parkingSpotMovementRepository.save(parkingSpotMovement)
    }
}