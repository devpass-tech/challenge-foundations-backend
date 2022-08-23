package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpotMovement
import io.devpass.parky.entity.ParkingSpotMovement
import io.devpass.parky.entity.Vehicle
import io.devpass.parky.requests.CheckInRequest
import org.springframework.stereotype.Service

@Service
class CheckInOutService(
    private val parkingSpotMovementService: ParkingSpotMovementService,
    private val vehicleService: VehicleService,
    private val parkingSpotService: ParkingSpotService,
) {
    fun createCheckIn(checkIn: CheckInRequest) {

        val vehicle = vehicleService.create(
            Vehicle(
                brand = checkIn.vehicle.brand,
                color = checkIn.vehicle.color,
                owner = checkIn.vehicle.owner
            )
        )

        val parkingSpot = parkingSpotService.findById(checkIn.parkingSpotId)
            ?: throw Exception("Vaga não encontrada")

        if (parkingSpot.inUseBy != null) {
            throw Exception("Vaga ocupada pelo veículo: $vehicle")
        }

        parkingSpot.inUseBy = vehicle.id
        parkingSpotService.update(parkingSpot)

        val parkingSpotMovement = ParkingSpotMovement(
            parkingSpotId = parkingSpot.id,
            event = "Check-in realizado pelo veículo: ${vehicle.id}")
        parkingSpotMovementService.create(parkingSpotMovement)
    }

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