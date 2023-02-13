package io.devpass.parky.requests

import com.fasterxml.jackson.annotation.JsonAlias

data class CheckOutRequest(
    @JsonAlias("vehicle_check_out")
    val vehicleCheckOut: VehicleCheckOut,
    @JsonAlias("spot_check_out")
    val spotCheckOut: SpotCheckOut
)

data class VehicleCheckOut(
    @JsonAlias("license_plate")
    var licensePlate: String,
    val brand: String,
    val color: String,
    val owner: String,
)

data class SpotCheckOut(
    val floor: Int,
    val spot: Int,
)
