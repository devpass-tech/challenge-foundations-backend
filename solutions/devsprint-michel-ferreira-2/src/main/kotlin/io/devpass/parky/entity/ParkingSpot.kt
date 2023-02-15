package io.devpass.parky.entity

import javax.persistence.*

@Entity
data class ParkingSpot(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Int = 0,
    var floor: Int,
    var spot: Int,
    @Column(name = "in_use_by", unique = true)
    var inUseBy: String?
)
