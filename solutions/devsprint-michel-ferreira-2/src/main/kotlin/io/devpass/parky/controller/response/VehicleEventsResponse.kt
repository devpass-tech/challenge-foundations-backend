package io.devpass.parky.controller.response

import io.devpass.parky.entity.ParkingSpotEvent
import io.devpass.parky.entity.Vehicle

data class VehicleEventsResponse(
    val vehicle: Vehicle,
    val history: List<ParkingSpotEvent>
)