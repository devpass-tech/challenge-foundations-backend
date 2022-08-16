package io.devpass.parky.controller

import io.devpass.parky.service.VehicleService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vehicles")
class VehicleController(
        private val vehicleService: VehicleService
) {}