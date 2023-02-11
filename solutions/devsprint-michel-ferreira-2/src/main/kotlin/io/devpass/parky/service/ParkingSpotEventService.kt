package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpotEvent
import io.devpass.parky.repository.ParkingSpotEventRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotEventService(
    private val parkingSpotEventRepository: ParkingSpotEventRepository
) {
    fun findByVehicleId(licensePlate: String): List<ParkingSpotEvent> =
        parkingSpotEventRepository.findByVehicleId(licensePlate)
}