package io.devpass.parky.service.tools

import io.devpass.parky.enums.EnumCheckInOut

data class ValidatedCheckOut(
    val vehicleId : String,
    val parkingSpotId : Int,
    val inUseBy : String,
    val floor : Int,
    val spot : Int,
    val event: EnumCheckInOut
)