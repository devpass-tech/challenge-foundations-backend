package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.entity.ParkingSpotEvent
import io.devpass.parky.entity.Vehicle
import io.devpass.parky.enums.EnumCheckInOut
import io.devpass.parky.framework.CheckInException
import io.devpass.parky.framework.CheckOutException
import io.devpass.parky.repository.ParkingSpotEventRepository
import io.devpass.parky.repository.ParkingSpotRepository
import io.devpass.parky.requests.CheckInRequest
import io.devpass.parky.requests.CheckOutRequest
import io.devpass.parky.service.tools.ValidatedCheckOut
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

        parkingSpotRepository.findByInUseBy(vehicle.id)
            ?.let {
                parkingSpotEventRepository.save(
                    ParkingSpotEvent(
                        parkingSpotId = it.id,
                        event = EnumCheckInOut.CHECKIN_DUPLICADO,
                        vehicleId = it.inUseBy!!
                    )
                )
                throw CheckInException("Car already parked in spot: ${it.spot} and floor: ${it.floor}")
            }

        val freeSpot = parkingSpotRepository.findByFloorAndSpot(
            floor = checkInRequest.spotCheckIn.floor,
            spot = checkInRequest.spotCheckIn.spot
        )

        // Validacao
        if (freeSpot == null) throw CheckInException("Free Spot not Found")
        if (freeSpot.inUseBy != null) throw CheckInException("Parking spot is not available")

        freeSpot.inUseBy = vehicle.id
        parkingSpotRepository.save(freeSpot)

        parkingSpotEventRepository.save(
            ParkingSpotEvent(
                parkingSpotId = freeSpot.id,
                event = EnumCheckInOut.CHECKIN,
                vehicleId = vehicle.id
            )
        )
    }

    fun checkOut(checkOutRequest: CheckOutRequest) {
        val vehicle = vehicleService.findVehicleLicensePlate(checkOutRequest.vehicleCheckOut.licensePlate)
            ?: throw CheckOutException("Vehicle with this license plate not Found, please Check-in first!")

        val parkingSpotCheckOut = parkingSpotRepository.findByFloorAndSpot(
            checkOutRequest.spotCheckOut.floor,
            checkOutRequest.spotCheckOut.spot
        )

        val validatedCheckOutRequest: ValidatedCheckOut = with(checkOutRequest) {
            validateParkingSpot(parkingSpotCheckOut)

            val inUseBy: String = parkingSpotCheckOut!!.inUseBy
                ?: throw CheckOutException("There are no vehicles at this spot")

            vehicleBelongsToTheSpot(vehicle.id, parkingSpotCheckOut.inUseBy)

            return@with ValidatedCheckOut(
                vehicleId = vehicle.id,
                inUseBy = inUseBy,
                parkingSpotId = parkingSpotCheckOut.id,
                floor = parkingSpotCheckOut.floor,
                spot = parkingSpotCheckOut.spot,
                event = EnumCheckInOut.CHECKOUT
            )
        }

        if (parkingSpotCheckOut != null) {
            parkingSpotCheckOut.inUseBy = null
            parkingSpotRepository.save(parkingSpotCheckOut)
        }

        val parkingSpotEventCheckout = ParkingSpotEvent(
            vehicleId = validatedCheckOutRequest.vehicleId,
            parkingSpotId = validatedCheckOutRequest.parkingSpotId,
            event = EnumCheckInOut.CHECKOUT,
            createdAt = LocalDateTime.now(),
        )

        parkingSpotEventRepository.save(parkingSpotEventCheckout)
    }

    fun validateParkingSpot(parkingSpot: ParkingSpot?): ParkingSpot =
        parkingSpot ?: throw CheckOutException("Invalid parking spot OR doesn't exist!")

    fun vehicleBelongsToTheSpot(vehicleId: String, inUseBy: String?): Boolean {
        if (vehicleId == inUseBy) {
            return true
        } else {
            throw CheckOutException("Invalid Spot! Please Insert correct spot corresponding to the vehicle location")
        }
    }
}