package io.devpass.parky.service.Utils

data class ValidatedCheckOut(
    val id : String,
    val parkingSpotId : Int,
    val inUseBy : String,
    val floor : Int,
    val spot : Int
)