package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.repository.ParkingSpotRepository
import io.devpass.parky.service.model.ParkingSpotFilter
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository
) {
    fun listParkingSpot(parkingSpotFilter: ParkingSpotFilter = ParkingSpotFilter()): List<ParkingSpot> {
        return parkingSpotRepository.findAll(parkingSpotFilter.toSpecification())
    }
}
