package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.exceptions.EmptyListException
import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository,
) {

    fun findAll(): List<ParkingSpot> {
        return parkingSpotRepository.findAll().toList()
    }

    fun findById(parkingSpotId: Int): ParkingSpot? {
        return parkingSpotRepository.findById(parkingSpotId).let {
            if (it.isPresent) it.get() else null
        }
    }

    fun findEmptyParkingSpotAtRandom(): ParkingSpot {
        val parkingSpotListIsEmpty = parkingSpotRepository.getRandomEmptyParkingSpot().isEmpty()
        if (parkingSpotListIsEmpty) {
            throw EmptyListException("No parking spot available!")
        } else {
            return parkingSpotRepository.getRandomEmptyParkingSpot().random()
        }
    }

    fun findEmptyParkingSpotByFloor(floor: Int): ParkingSpot {
        val parkingSpotByFloorIsEmpty: Boolean = parkingSpotRepository.getParkingSpotByFloor(floor).isEmpty()
        if (parkingSpotByFloorIsEmpty) {
            throw EmptyListException("No parking spot available!")
        } else {
            return parkingSpotRepository.getParkingSpotByFloor(floor).random()
        }
    }

    fun update(parkingSpot: ParkingSpot): ParkingSpot {
        return parkingSpotRepository.save(parkingSpot)
    }
}