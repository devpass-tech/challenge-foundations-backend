package io.devpass.parky.controller.request

import com.fasterxml.jackson.annotation.JsonAlias

data class VehicleRequest(
    var brand: String,
    var color: String,
    var owner: String,
    @JsonAlias("license_plate")
    var licensePlate: String
)