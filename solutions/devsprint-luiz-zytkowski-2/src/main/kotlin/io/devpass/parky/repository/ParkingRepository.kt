package io.devpass.parky.repository

import io.devpass.parky.entity.ParkingSpot
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParkingRepository: CrudRepository<ParkingSpot, Int> {
}