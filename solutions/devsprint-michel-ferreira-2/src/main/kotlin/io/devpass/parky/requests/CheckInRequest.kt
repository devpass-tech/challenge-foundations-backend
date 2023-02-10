package io.devpass.parky.requests

data class CheckInRequest(
    val vehicleCheckIn: VehicleCheckIn,
    val spotCheckIn: SpotCheckIn
)

data class VehicleCheckIn(
    var licensePlate: String,
    val brand: String,
    val color: String,
    val owner: String,
)

data class SpotCheckIn(
    val floor: Int,
    val spot: Int,
)