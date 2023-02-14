package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository
) {
    fun listParkingSpot(InUse: Boolean? = null): List<ParkingSpot> {
        if (InUse == null) {
            parkingSpotRepository.getAllParkingSpots()
        }
        if (InUse != true) {
            parkingSpotRepository.findByInUseBy()
        } else {
            parkingSpotRepository.findByInUseByIsNull()
        }

        return listParkingSpot()
    }
}
