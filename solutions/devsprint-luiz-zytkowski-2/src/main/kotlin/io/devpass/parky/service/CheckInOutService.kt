package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.entity.ParkingSpotMovement
import io.devpass.parky.entity.Vehicle
import io.devpass.parky.framework.OwnedException
import io.devpass.parky.requests.CheckInRequest
import io.devpass.parky.service.exceptions.CheckInException
import io.devpass.parky.service.exceptions.CheckOutException
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
            ?: throw CheckInException("Vaga não encontrada")

        if (parkingSpot.inUseBy != null) {
            throw CheckInException("Vaga ocupada pelo veículo de id: ${parkingSpot.inUseBy}")
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

    fun checkOut(parkingSpotId: Int) {
        val parkingSpot = parkingSpotService.findById(parkingSpotId)
            ?: throw CheckOutException("Vaga não encontrada")

        if (parkingSpot.inUseBy == null) {
            throw CheckOutException("Vaga livre")
        }

        val parkingSpotMovement = ParkingSpotMovement(
            parkingSpotId = parkingSpotId,
            event = "Check-out realizado pelo veículo: ${parkingSpot.inUseBy}"
        )
        parkingSpotMovementService.create(parkingSpotMovement)

        parkingSpot.inUseBy = null
        parkingSpotService.update(parkingSpot)
        availableParkingSpotNotificationService.checkOutNotification(parkingSpot)
    }

    fun checkOutFromAdmin(parkingSpot: ParkingSpot) {
        val parkingSpotMovement = ParkingSpotMovement(
            parkingSpotId = parkingSpot.id,
            event = "Check-out realizado pelo administrador: ${parkingSpot.inUseBy}"
        )
        parkingSpotMovementService.create(parkingSpotMovement)

        parkingSpot.inUseBy = null
        parkingSpotService.update(parkingSpot)
    }

    fun cleanAllParkingSpots() {
        val listOfParkingSpots = parkingSpotService.findAll()
        listOfParkingSpots.forEach{
            if (it.inUseBy == null) {
                println("Não precisei limpar a vaga  pois não estava em uso")
            } else {
                checkOutFromAdmin(it)
            }
        }
        println("Vagas liberadas com sucesso")
    }
}