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
        val listOfParkingSpot= parkingSpotRepository.getRandomEmptyParkingSpot()
        if (listOfParkingSpot.isEmpty()) {
            throw EmptyListException("No parking spot available!")
        } else {
            return listOfParkingSpot.random()
        }
    }

    fun findEmptyParkingSpotByFloor(floor: Int): ParkingSpot {
        val listOfParkingSpot= parkingSpotRepository.getParkingSpotByFloor(floor)
        if (listOfParkingSpot.isEmpty()) {
            throw EmptyListException("No parking spot available!")
        } else {
            return listOfParkingSpot.random()
        }
    }

    fun update(parkingSpot: ParkingSpot): ParkingSpot {
        return parkingSpotRepository.save(parkingSpot)
    }
}