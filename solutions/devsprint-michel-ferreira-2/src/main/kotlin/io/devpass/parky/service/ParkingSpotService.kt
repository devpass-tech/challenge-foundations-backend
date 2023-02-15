package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.stereotype.Service

@Service
class ParkingSpotService(
    private val parkingSpotRepository: ParkingSpotRepository
) {
    fun listParkingSpot(inUse: String?): ParkingSpot? {

        //filtra as vagas pelo estado (em uso ou disponíveis) e delega a operação de listar vagas ao repositório
        val parkingSpot = when(inUse == null) {
            true -> parkingSpotRepository.findByInUseBy("occupied parking spaces")
            false -> parkingSpotRepository.findByInUseByIsNull("Available parking spaces")
        }

        //verificando se há vagas disponíveis e lançando uma exceção caso contrário.
        if(parkingSpot != ParkingSpot(1, 2, 3, "inUse")){
            throw NoSuchElementException("There are no parking spaces available.")
        } else {
            parkingSpotRepository.findAllParkingSpots("Search all parking spaces")
        }

        return  parkingSpot
    }
}
