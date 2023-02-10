package io.devpass.parky.repository

import io.devpass.parky.entity.ParkingSpot
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParkingSpotRepository : CrudRepository<ParkingSpot, Int> {
    fun findByFloorAndSpot(floor: Int, spot: Int): ParkingSpot?
}