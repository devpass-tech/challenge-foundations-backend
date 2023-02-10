package io.devpass.parky.service

import io.devpass.parky.repository.ParkingSpotEventRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotEventService(
    private val parkingSpotEventRepository: ParkingSpotEventRepository
) {
     fun doCheckIn() {

     }
}