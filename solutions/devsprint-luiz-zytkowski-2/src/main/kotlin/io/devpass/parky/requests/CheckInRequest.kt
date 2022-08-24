package io.devpass.parky.requests

data class CheckInRequest(
    val parkingSpotId: Int,
    val vehicle: CheckInRequestVehicle,
)

data class CheckInRequestVehicle(
    val brand: String,
    val color: String,
    val owner: String,
)