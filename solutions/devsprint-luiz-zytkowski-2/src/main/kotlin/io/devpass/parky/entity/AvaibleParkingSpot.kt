package io.devpass.parky.entity

import javax.persistence.Entity
import javax.persistence.Id
import kotlin.math.absoluteValue
import kotlin.random.Random

@Entity
data class AvaibleParkingSpot(
    @Id
    var id : Int = Random.nextInt().absoluteValue,
    @Id
    var parkingSpotId: Int = Random.nextInt().absoluteValue,
    val email: String
)