package io.devpass.parky.entity

import javax.persistence.*

@Entity
data class ParkSpotEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int,

    @Column(name = "parking_spot_id", nullable = false)
    val parkSpotId: Int,

    @Column(name = "event", nullable = false)
    val event: String,

    @Column(name = "created_at", nullable = false)
    val createdAt: String
)