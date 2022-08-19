package io.devpass.parky.repository

import io.devpass.parky.entity.ParkingSpot
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParkingSpotRepository : CrudRepository<ParkingSpot, Int>{

    //ver a melhor ou a forma correta de fazer a query
    @Query("SELECT  from ParkingSpot where ParkingSpot.inUseBy = inUseBy")
    fun getRandomEmptyParkingSpot(): List<ParkingSpot>
}