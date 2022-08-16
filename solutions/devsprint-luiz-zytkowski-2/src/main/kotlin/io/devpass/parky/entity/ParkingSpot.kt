package io.devpass.parky.entity

import org.jetbrains.annotations.NotNull
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ParkingSpot(
    @Id
    var id: Int,
    var floor: Int,
    var spot: Int,
    var inUseBy: Int,
    ) {
}