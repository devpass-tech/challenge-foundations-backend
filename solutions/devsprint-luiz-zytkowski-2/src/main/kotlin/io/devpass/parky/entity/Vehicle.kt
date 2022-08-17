package io.devpass.parky.entity

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id
import kotlin.math.absoluteValue
import kotlin.random.Random

@Entity
data class Vehicle(
    @Id
    var id: Int = Random.nextInt().absoluteValue,
    var brand: String,
    var color: String,
    var owner: String,
    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.now(),
)

