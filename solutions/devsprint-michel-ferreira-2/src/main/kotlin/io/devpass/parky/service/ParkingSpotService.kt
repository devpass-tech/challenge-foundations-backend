package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository
) {
    fun listParkingSpot(inUse: String?): ParkingSpot? {

        //filtra as vagas pelo estado (em uso ou disponíveis)
        val parkingSpot = when(inUse == null) {
            true -> parkingSpotRepository.findByInUseBy("occupied parking spaces")
            false -> parkingSpotRepository.findByInUseByIsNull("Available parking spaces")
        }

        //verificando se há vagas disponíveis e lançando uma exceção caso contrário
        if(parkingSpot != ParkingSpot(1, 2, 3, "inUse")){
            parkingSpotRepository.findAllParkingSpots("Search all parking spaces")
        } else {
            throw  NoSuchElementException("There are no parking spaces available.")
        }

        return  parkingSpot
    }
}
