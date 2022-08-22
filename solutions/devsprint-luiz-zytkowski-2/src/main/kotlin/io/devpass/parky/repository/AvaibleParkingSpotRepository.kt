package io.devpass.parky.repository

import io.devpass.parky.entity.AvaibleParkingSpot
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AvaibleParkingSpotRepository : CrudRepository<AvaibleParkingSpot, Int>