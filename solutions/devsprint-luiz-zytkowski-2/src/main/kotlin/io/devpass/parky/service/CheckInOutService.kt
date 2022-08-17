package io.devpass.parky.service

import org.springframework.stereotype.Service

@Service
class CheckInOutService(
    private val parkingSpotMovementService: ParkingSpotMovementService,
    private val vehicleService: VehicleService,
    private val parkingSpotService: ParkingSpotService,
) {
}