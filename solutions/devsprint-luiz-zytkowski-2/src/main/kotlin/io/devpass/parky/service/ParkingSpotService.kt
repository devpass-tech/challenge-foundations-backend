package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
        private val parkingSpotRepository: ParkingSpotRepository,
        private val checkInOutService: CheckInOutService
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
        return parkingSpotRepository.getRandomEmptyParkingSpot().random()
    }

    fun findEmptyParkingSpotByFloor(floor: Int): ParkingSpot {
        return parkingSpotRepository.getParkingSpotByFloor(floor).random()
    }

    fun update(parkingSpot: ParkingSpot): ParkingSpot {
        return parkingSpotRepository.save(parkingSpot)
    }

    fun cleanAllParkingSpots() {
        val listOfParkingSpots = this.findAll()
        listOfParkingSpots.forEach()
        {
            if (it.inUseBy == null) {
                println("Não precisei limpar a vaga  pois não estava em uso")
                return@forEach
            } else {
                checkInOutService.checkOutFromAdmin(it.id)
            }
            println(it.toString())
        }
    }

    fun checkStatusOfParkingSpots(): Boolean {
        val listOfParkingSpots = this.findAll()
        listOfParkingSpots.forEach() {
            if (it.inUseBy !== null) {
                return false
            }
        }
        return true
    }
}

