package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpotMovement
import io.devpass.parky.entity.Vehicle
import io.devpass.parky.framework.OwnedException
import io.devpass.parky.requests.CheckInRequest
import org.springframework.stereotype.Service

@Service
class CheckInOutService(
    private val parkingSpotMovementService: ParkingSpotMovementService,
    private val vehicleService: VehicleService,
    private val parkingSpotService: ParkingSpotService,
    private val availableParkingSpotNotificationService: AvailableParkingSpotNotificationService,
) {
    fun createCheckIn(checkInRequest: CheckInRequest) {
        val parkingSpot = parkingSpotService.findById(checkInRequest.parkingSpotId)
            ?: throw OwnedException("Vaga não encontrada")

        if (parkingSpot.inUseBy != null) {
            throw OwnedException("Vaga ocupada pelo veículo de id: ${parkingSpot.inUseBy}")
        }

        val vehicle = vehicleService.create(
            Vehicle(
                brand = checkInRequest.vehicle.brand,
                color = checkInRequest.vehicle.color,
                owner = checkInRequest.vehicle.owner
            )
        )

        parkingSpot.inUseBy = vehicle.id
        parkingSpotService.update(parkingSpot)

        val parkingSpotMovement = ParkingSpotMovement(
            parkingSpotId = parkingSpot.id,
            event = "Check-in realizado pelo veículo: ${vehicle}"
        )
        parkingSpotMovementService.create(parkingSpotMovement)
    }

    fun removeCheckIn(parkingSpotId: Int) {
        val parkingSpot = parkingSpotService.findById(parkingSpotId)
            ?: throw Exception("Vaga não encontrada")

        if (parkingSpot.inUseBy == null) {
            throw Exception("Vaga livre")
        }

        val parkingSpotMovement = ParkingSpotMovement(
            parkingSpotId = parkingSpotId,
            event = "Check-out realizado pelo veículo: ${parkingSpot.inUseBy}"
        )
        parkingSpotMovementService.create(parkingSpotMovement)

        parkingSpot.inUseBy = null
        parkingSpotService.update(parkingSpot)
        availableParkingSpotNotificationService.checkOutNotification(parkingSpotId)
    }

    fun checkOutFromAdmin(parkingSpotId: Int) {
        val parkingSpot = parkingSpotService.findById(parkingSpotId)
            ?: throw Exception("Vaga não encontrada")

        if (parkingSpot.inUseBy == null) {
            throw Exception("Vaga livre")
        }

        val parkingSpotMovement = ParkingSpotMovement(
            parkingSpotId = parkingSpotId,
            event = "Check-out realizado pelo administrador: ${parkingSpot.inUseBy}"
        )
        parkingSpotMovementService.create(parkingSpotMovement)

        parkingSpot.inUseBy = null
        parkingSpotService.update(parkingSpot)
    }
    fun cleanAllParkingSpots() {
        val listOfParkingSpots = parkingSpotService.findAll()
        listOfParkingSpots.forEach()
        {
            if (it.inUseBy == null) {
                throw Exception("Não precisei limpar a vaga  pois não estava em uso")
                return@forEach
            } else {
                checkOutFromAdmin(it.id)
            }
            throw Exception("Vagas liberadas com sucesso")
        }
    }
}