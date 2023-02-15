package io.devpass.parky.repository

import io.devpass.parky.entity.ParkingSpotEvent
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParkingSpotEventRepository : CrudRepository<ParkingSpotEvent, Int> {
    fun findByVehicleId(vehicleId: String): List<ParkingSpotEvent>
}