package io.devpass.parky.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/vehicle")
class VehicleController(
        private val vehicleService: VehicleService
) {}