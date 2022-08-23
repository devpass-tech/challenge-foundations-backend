package io.devpass.parky.repository

import io.devpass.parky.entity.AvailableParkingSpotNotification
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AvailableParkingSpotNotificationRepository : CrudRepository<AvailableParkingSpotNotification, Int>