package io.devpass.parky.provider.response

import com.fasterxml.jackson.annotation.JsonAlias

data class VehicleVerificationResponse(
    @JsonAlias("license_plate")
    val licensePlate: String,
    val status: String
)