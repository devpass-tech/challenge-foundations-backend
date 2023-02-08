package io.devpass.parky.service

import io.devpass.parky.repository.ParkSpotRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkSpotRepository
) {

}