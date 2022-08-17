package io.devpass.parky.service

import io.devpass.parky.repository.ParkingSpotMovementRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotMovementService(
    private val parkingSpotMovementRepository: ParkingSpotMovementRepository
) {
}