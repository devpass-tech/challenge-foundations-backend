package io.devpass.parky.entity

import javax.persistence.Entity
import javax.persistence.Id
import kotlin.math.absoluteValue
import kotlin.random.Random

@Entity
data class AvailableParkingSpotNotification(
    @Id
    var id: Int = Random.nextInt().absoluteValue,
    var parkingSpotId: Int,
    val email: String,
)