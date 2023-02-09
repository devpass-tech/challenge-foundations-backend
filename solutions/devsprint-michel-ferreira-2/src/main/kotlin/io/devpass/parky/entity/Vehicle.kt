package io.devpass.parky.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
data class Vehicle(
    @Id
    @Column(name = "id")
    var id: String = UUID.randomUUID().toString(),
    var brand: String,
    var color: String,
    var owner: String,
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime? = LocalDateTime.now()
)
