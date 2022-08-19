package io.devpass.parky.requests

data class CheckInRequest(
    var vehicleId: Int,
    var vehicleBrand: String,
    var vehicleColor: String,
    var vehicleOwner: String,
    var vehicleCreatedAt: String,
    var parkingSpotId: Int,
) {}