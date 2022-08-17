package io.devpass.parky.entity

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ParkingSpot(
    @Id
    var id: Int,
    var floor: Int,
    var spot: Int,
    var inUseBy: Int,
)