package io.devpass.parky.repository

import io.devpass.parky.entity.ParkingSpot
import org.springframework.stereotype.Repository

@Repository
interface ParkingSpaceMovementRepository {
    fun findByParkingSpot(id: Int): List<ParkingSpot>
}


