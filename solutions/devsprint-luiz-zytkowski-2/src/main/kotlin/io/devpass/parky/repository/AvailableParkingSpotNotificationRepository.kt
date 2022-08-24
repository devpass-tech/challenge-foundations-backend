package io.devpass.parky.repository

import io.devpass.parky.entity.AvailableParkingSpotNotification
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AvailableParkingSpotNotificationRepository : CrudRepository<AvailableParkingSpotNotification, Int> {
    @Query("SELECT apsn from AvailableParkingSpotNotification apsn where apsn.parkingSpotId = ?1")
    fun findByParkingSpotId(parkingSpotId: Int): List<AvailableParkingSpotNotification>
}