package io.devpass.parky.service

import io.devpass.parky.repository.AvaibleParkingSpotRepository
import org.springframework.stereotype.Service

@Service
class AvaibleParkingSpotService(
    private val avaibleParkingSpotRepository: AvaibleParkingSpotRepository
)