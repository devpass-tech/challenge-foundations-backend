package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.entity.ParkingSpotEvent
import io.devpass.parky.entity.Vehicle
import io.devpass.parky.framework.CheckInException
import io.devpass.parky.framework.CheckOutException
import io.devpass.parky.repository.ParkingSpotEventRepository
import io.devpass.parky.repository.ParkingSpotRepository
import io.devpass.parky.requests.CheckInRequest
import io.devpass.parky.requests.CheckOutRequest
import io.devpass.parky.service.Utils.ValidatedCheckOut
import org.springframework.stereotype.Service

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

    fun checkOut(checkOutRequest: CheckOutRequest) {
        val vehicle = vehicleService.findVehicleLicensePlate(checkOutRequest.vehicleCheckOut.licensePlate)
            ?: throw CheckOutException("Vehicle not Found, please Check-in first!")

        val parkingSpotCheckOut = parkingSpotRepository.findByFloorAndSpot(
            checkOutRequest.spotCheckOut.floor,
            checkOutRequest.spotCheckOut.spot
        )
        lateinit var inUseBy: String

        val validatedCheckOutRequest: ValidatedCheckOut = with(checkOutRequest) {
            validateParkingSpot(parkingSpotCheckOut)

            validateParkingSpotIsEmpty(parkingSpotCheckOut!!.inUseBy)

            inUseBy = (vehicleBelongsToTheSpot(vehicle.id, parkingSpotCheckOut.inUseBy))

            return@with ValidatedCheckOut(
                id = vehicle.id,
                inUseBy = inUseBy,
                parkingSpotId = parkingSpotCheckOut.id,
                floor = parkingSpotCheckOut.floor,
                spot = parkingSpotCheckOut.spot
            )
        }

        if (parkingSpotCheckOut != null) {
            parkingSpotCheckOut.inUseBy = null
            parkingSpotRepository.save(parkingSpotCheckOut)
        }

        val parkingSpotEventCheckout = ParkingSpotEvent(
            parkingSpotId = validatedCheckOutRequest.parkingSpotId,
            event = "Check-out"
        )

        parkingSpotEventRepository.save(parkingSpotEventCheckout)
    }

    fun validateParkingSpot(parkingSpot: ParkingSpot?): ParkingSpot =
        parkingSpot ?: throw CheckOutException("Invalid parking spot OR doesn't exist!")

    fun validateParkingSpotIsEmpty(inUseBy: String?): String? =
        inUseBy ?: throw CheckOutException("There are no vehicles at this spot")

    fun vehicleBelongsToTheSpot(vehicleId: String, inUseBy: String?): String {
        if (vehicleId == inUseBy) {
            return inUseBy
        } else {
            throw CheckOutException("Invalid Spot! Please Insert correct spot corresponding to the vehicle location")
        }
    }

    fun validateIfCarIsAlreadyParked(inUseBy: String?, vehicleId: String?): Boolean = (inUseBy == vehicleId)

}