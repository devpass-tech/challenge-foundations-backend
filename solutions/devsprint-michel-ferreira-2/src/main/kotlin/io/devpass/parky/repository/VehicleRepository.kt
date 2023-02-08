package io.devpass.parky.repository

import io.devpass.parky.entity.VacancyMovement
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository: CrudRepository<VacancyMovement, Int>