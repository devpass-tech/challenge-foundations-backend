package io.devpass.parky.service

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

        vehicleService.create(
            Vehicle(
                brand = checkIn.vehicleBrand,
                color = checkIn.vehicleColor,
                owner = checkIn.vehicleOwner))

        val parkingSpot = parkingSpotService.findById(checkIn.parkingSpotId)

        if (parkingSpot != null && parkingSpot.inUseBy == null) {
            val parkingSpotMovement = ParkingSpotMovement(parkingSpotId = parkingSpot.id, event = "Check-in")
            parkingSpotMovementService.create(parkingSpotMovement)
        }
    }
}