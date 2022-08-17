package io.devpass.parky.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ParkingSpotMovement(
        @Id
        var parking_spot_id: Int,
        var event: Int,
        @CreationTimestamp
        var createdAt: LocalDateTime
)








