package io.devpass.parky.requests

data class AvailableParkingSpotNotificationRequest(
    val parkingSpotId: Int,
    val email: String,
)