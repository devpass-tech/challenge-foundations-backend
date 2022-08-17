package io.devpass.parky.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import kotlin.math.absoluteValue
import kotlin.random.Random

@Entity
data class ParkingSpot(
        @Id
        var id: Int = Random.nextInt().absoluteValue,
        var parking_spot_id: Int,
        var floor: Int,
        var spot: Int,
        var inUseBy: Int,
        @CreationTimestamp
        var createdAt: LocalDateTime = LocalDateTime.now()
)








