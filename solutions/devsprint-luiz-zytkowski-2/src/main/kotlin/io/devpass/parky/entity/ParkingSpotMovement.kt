package io.devpass.parky.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ParkingSpotMovement(
        @Id
        var parkingSpotId: Int,
        var event: Int,
        @CreationTimestamp
        var createdAt: LocalDateTime
)