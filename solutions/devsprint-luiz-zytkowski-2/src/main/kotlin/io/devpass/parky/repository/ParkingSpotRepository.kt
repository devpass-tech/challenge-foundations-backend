package io.devpass.parky.repository

import io.devpass.parky.entity.ParkingSpot
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParkingSpotRepository : CrudRepository<ParkingSpot, Int> {

    @Query("SELECT ps from ParkingSpot ps where ps.inUseBy is null")
    fun getRandomEmptyParkingSpot(): List<ParkingSpot>

    @Query("SELECT ps from ParkingSpot ps where ps.floor = ?1 and ps.inUseBy is null")
    fun getParkingSpotByFloor(floor: Int): List<ParkingSpot>
}
