package io.devpass.parky.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import kotlin.math.absoluteValue
import kotlin.random.Random

@Entity
data class ParkingSpotMovement(
    @Id
    var id: Int = Random.nextInt().absoluteValue,
    var parkingSpotId: Int,
    var event: String,
    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.now(),
)