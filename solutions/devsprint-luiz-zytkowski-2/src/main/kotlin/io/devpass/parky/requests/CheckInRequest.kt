package io.devpass.parky.requests

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.entity.Vehicle

data class CheckInRequest(
    var vehicle: Vehicle,
    var parkingSpot: ParkingSpot,
) {
}