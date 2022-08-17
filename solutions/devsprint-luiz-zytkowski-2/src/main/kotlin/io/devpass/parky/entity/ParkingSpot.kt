package io.devpass.parky.entity

import javax.persistence.Entity
import javax.persistence.Id
import kotlin.math.absoluteValue
import kotlin.random.Random

@Entity
data class ParkingSpot(
    @Id
    var id: Int = Random.nextInt().absoluteValue,
    var floor: Int,
    var spot: Int,
    var inUseBy: Int? = null,
)