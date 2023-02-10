package io.devpass.parky.repository

import io.devpass.parky.entity.Vehicle
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository : CrudRepository<Vehicle, String> {
    fun findByLicensePlate(licensePlate: String): Vehicle?
}