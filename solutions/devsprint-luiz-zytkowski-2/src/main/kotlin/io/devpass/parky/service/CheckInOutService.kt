package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpotMovement
import org.springframework.stereotype.Service

@Service
class CheckInOutService(
    private val parkingSpotMovementService: ParkingSpotMovementService,
    private val vehicleService: VehicleService,
    private val parkingSpotService: ParkingSpotService,
) {
    fun removeCheckIn(parkingSpotId: Int) {
        val parkingSpot = parkingSpotService.findById(parkingSpotId)
            ?: throw Exception("Vaga não encontrada")

        if (parkingSpot.inUseBy == null) {
            throw Exception("Vaga livre")
        }

        val parkingSpotMovement = ParkingSpotMovement(
            parkingSpotId = parkingSpotId,
            event = "Check-out realizado pelo veículo: ${parkingSpot.inUseBy}"
        )
        parkingSpotMovementService.create(parkingSpotMovement)

        parkingSpot.inUseBy = null
        parkingSpotService.update(parkingSpot)
    }
}