package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpotEvent
import io.devpass.parky.entity.Vehicle
import io.devpass.parky.framework.CheckInException
import io.devpass.parky.repository.ParkingSpotEventRepository
import io.devpass.parky.repository.ParkingSpotRepository
import io.devpass.parky.requests.CheckInRequest
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
        if (freeSpot.inUseBy != null) throw CheckInException("Parking spot is not available")

        freeSpot.inUseBy = vehicle.id
        parkingSpotRepository.save(freeSpot)

        parkingSpotEventRepository.save(
            ParkingSpotEvent(
                parkingSpotId = freeSpot.id,
                event = "Check-in",
                vehicleId = vehicle.id
            )
        )
    }
}