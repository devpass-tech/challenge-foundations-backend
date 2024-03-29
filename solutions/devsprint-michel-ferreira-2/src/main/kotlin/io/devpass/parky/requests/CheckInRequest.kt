package io.devpass.parky.requests

import com.fasterxml.jackson.annotation.JsonAlias

data class CheckInRequest(
    @JsonAlias("vehicle_check_in")
    val vehicleCheckIn: VehicleCheckIn,
    @JsonAlias("spot_check_in")
    val spotCheckIn: SpotCheckIn
)

data class VehicleCheckIn(
    @JsonAlias("license_plate")
    var licensePlate: String,
    val brand: String,
    val color: String,
    val owner: String,
)

data class SpotCheckIn(
    val floor: Int,
    val spot: Int,
)
