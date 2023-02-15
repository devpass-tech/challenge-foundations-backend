package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpotEvent
import io.devpass.parky.repository.ParkingSpotEventRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpaceMovementService(
    private val parkingSpotEventRepository: ParkingSpotEventRepository
) {
    fun listParkingSpaceMovement(id: Int): List<ParkingSpotEvent> = parkingSpotEventRepository.findByParkingSpotId(id)
}
