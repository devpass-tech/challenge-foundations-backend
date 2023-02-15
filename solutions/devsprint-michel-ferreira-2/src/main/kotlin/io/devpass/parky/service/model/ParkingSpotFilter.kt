package io.devpass.parky.service.model

import io.devpass.parky.entity.ParkingSpot
import org.springframework.data.jpa.domain.Specification

data class ParkingSpotFilter(
    val floor: Int? = null,
    val spot: Int? = null,
    val inUse: Boolean? = null
) : Specificatiable<ParkingSpot> {
    override fun toSpecification(): Specification<ParkingSpot> {
        // Select * from ParkingSpot as ps Where 1 = 1 && ps.floor = $floor
        // && ps.inUseBy not null
        var specification = Specification.where<ParkingSpot>(null)

        if (floor != null) {
            specification = specification.and { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<Any>("floor"), floor)
            }
        }

        if (spot != null) {
            specification = specification.and { root, _, criteriaBuilder ->
                criteriaBuilder.equal(root.get<Any>("spot"), spot)
            }
        }

        if (inUse != null && inUse == true) {
            specification = specification.and { root, _, criteriaBuilder ->
                criteriaBuilder.isNotNull(root.get<Any>("inUseBy"))
            }
        }

        if (inUse != null && inUse == false) {
            specification = specification.and { root, _, criteriaBuilder ->
                criteriaBuilder.isNull(root.get<Any>("inUseBy"))
            }
        }

        return specification
    }
}

interface Specificatiable<T> {
    fun toSpecification(): Specification<T>
}
