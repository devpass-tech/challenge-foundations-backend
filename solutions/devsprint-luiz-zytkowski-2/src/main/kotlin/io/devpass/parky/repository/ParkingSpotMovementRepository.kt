package io.devpass.parky.repository

import io.devpass.parky.entity.ParkingSpotMovement
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParkingSpotMovementRepository : CrudRepository<ParkingSpotMovement, Int> {
    @Query("select psm from ParkingSpotMovement psm where psm.parkingSpotId = ?1")
    fun listAllMovement(parkingSpotId: Int): List<ParkingSpotMovement>
}