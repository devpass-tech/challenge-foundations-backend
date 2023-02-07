package io.devpass.parky.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ParkingSpot(
    @Id
    @Column(name = "id", nullable = false)
    var id: Int,
    var spot: Int,
    var inUseBy: Int?
)