package io.devpass.parky.service

import io.devpass.parky.repository.VehicleRepository
import org.springframework.stereotype.Service

@Service
class CheckService(
    private val vehicleRepository: VehicleRepository
)