package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpotMovement
import io.devpass.parky.repository.ParkingSpotMovementRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotMovementService(
    private val parkingSpotMovementRepository: ParkingSpotMovementRepository,
) {
    fun create(parkingSpotMovement: ParkingSpotMovement) {
        parkingSpotMovementRepository.save(parkingSpotMovement)
    }
}