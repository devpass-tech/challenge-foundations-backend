package io.devpass.parky.repository

import io.devpass.parky.entity.ParkingSpotMovement
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParkingSpotMovementRepository : CrudRepository<ParkingSpotMovement, Int> {
}