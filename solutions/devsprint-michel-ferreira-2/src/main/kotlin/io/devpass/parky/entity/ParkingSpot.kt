package io.devpass.parky.entity

import javax.persistence.*

@Entity
data class ParkingSpot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int = 0,
    @Column(name = "in_use_by")
    var inUseBy: String?,
    @Column(unique = true)
    var spot: Int,
    @Column(unique = true)
    var floor: String
)
