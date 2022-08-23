package io.devpass.parky.repository

import io.devpass.parky.entity.AvailableParkingSpotNotification
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AvailableParkingSpotNotificationRepository : CrudRepository<AvailableParkingSpotNotification, Int> {

    @Query("select apsn from AvailableParkingSpotNotification apsn where apsn.parkingSpotId = ?1 and apsn.email = ?2")
    fun findEmailAndParkingSpotId(parkingSpotId: Int, email: String): List<AvailableParkingSpotNotification>
}