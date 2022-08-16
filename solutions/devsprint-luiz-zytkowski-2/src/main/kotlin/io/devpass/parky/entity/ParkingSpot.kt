package io.devpass.parky.entity

import org.jetbrains.annotations.NotNull
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ParkingSpot(
    @Id
    var id: Int,
    @NotNull
    var floor: Int,
    @NotNull
    var spot: Int,

    var in_use_by: Int) {
}