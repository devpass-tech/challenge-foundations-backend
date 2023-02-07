package io.devpass.parky.entity

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class VacancyMovement (
    @Id
    @Column(name = "id", nullable = false)
    var vehicleId: Int,
    var vehicleName: String,
    var entryDate: LocalDate,
    var timeSpent: LocalDateTime
)