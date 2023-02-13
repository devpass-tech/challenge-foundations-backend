package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpotEvent
import io.devpass.parky.entity.Vehicle
import io.devpass.parky.framework.CheckInException
import io.devpass.parky.framework.CheckOutException
import io.devpass.parky.repository.ParkingSpotEventRepository
import io.devpass.parky.repository.ParkingSpotRepository
import io.devpass.parky.requests.CheckInRequest
import io.devpass.parky.requests.CheckOutRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CheckInOutService(
    private val vehicleService: VehicleService,
    private val parkingSpotEventRepository: ParkingSpotEventRepository,
    private val parkingSpotRepository: ParkingSpotRepository
) {
    fun checkIn(checkInRequest: CheckInRequest) {
        val vehicle = vehicleService.createIfNotExists(
            (Vehicle(
                licensePlate = checkInRequest.vehicleCheckIn.licensePlate,
                brand = checkInRequest.vehicleCheckIn.brand,
                color = checkInRequest.vehicleCheckIn.color,
                owner = checkInRequest.vehicleCheckIn.owner
            ))
        )

        val freeSpot = parkingSpotRepository.findByFloorAndSpot(
            floor = checkInRequest.spotCheckIn.floor,
            spot = checkInRequest.spotCheckIn.spot
        )

        // Validacao
        if (freeSpot == null) throw CheckInException("Free Spot not Found")

        validateIfCarIsAlreadyParked(freeSpot.inUseBy, vehicle.id)

        if (freeSpot.inUseBy != null) throw CheckInException("Parking spot is not available")

        freeSpot.inUseBy = vehicle.id
        parkingSpotRepository.save(freeSpot)

        parkingSpotEventRepository.save(
            ParkingSpotEvent(
                parkingSpotId = freeSpot.id,
                event = "Check-in"
            )
        )
    }

    fun checkOut(checkOutRequest: CheckOutRequest): Boolean {
        val vehicle = vehicleService.findVehicleLicensePlate(checkOutRequest.vehicleCheckOut.licensePlate)
            ?: throw CheckOutException("Vehicle not Found!")

        val parkingSpotCheckOut = parkingSpotRepository.findByFloorAndSpot(
            checkOutRequest.spotCheckOut.floor,
            checkOutRequest.spotCheckOut.spot
        )

        if (parkingSpotIsEmpty(parkingSpotCheckOut!!.inUseBy)) {
            throw CheckOutException("There are no vehicles at this spot")
        }

        if (!vehicleBelongsToTheSpot(vehicle.id, parkingSpotCheckOut.inUseBy)) {
            throw CheckOutException("This Vehicle is located at a different spot, please insert the correct location")
        }

        parkingSpotCheckOut.inUseBy = null

        parkingSpotRepository.save(parkingSpotCheckOut)

        val parkingSpotEventCheckout = ParkingSpotEvent(
            id = 0,
            parkingSpotId = parkingSpotCheckOut.id,
            event = "Check-out",
            createdAt = LocalDateTime.now()
        )

        parkingSpotEventRepository.save(parkingSpotEventCheckout)
        return true
    }

    fun parkingSpotIsEmpty(inUseBy : String?): Boolean {
        if (inUseBy == null) {
            return true
        }
        return false
    }

    fun vehicleBelongsToTheSpot(vehicleId: String, inUseBy: String?): Boolean {
        if (vehicleId == inUseBy) {
            return true
        }
        return false
    }

    fun validateIfCarIsAlreadyParked(inUseBy : String?, vehicleId : String?): Boolean {
        if (inUseBy == vehicleId) {
            throw CheckInException("You are already parked in this spot")
        }
        return true
    }
}