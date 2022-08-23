package io.devpass.parky.service

import io.devpass.parky.entity.AvailableParkingSpotNotification
import io.devpass.parky.repository.AvailableParkingSpotNotificationRepository
import io.devpass.parky.requests.AvailableParkingSpotNotificationRequest
import org.springframework.stereotype.Service

@Service
class AvailableParkingSpotNotificationService(
    private val availableParkingSpotNotificationRepository: AvailableParkingSpotNotificationRepository,
) {

    fun createNotification(availableParkingSpotNotificationRequest: AvailableParkingSpotNotificationRequest) {
        //checar se há notificacao criada para aquele email && vaga

        val notificationCreated = availableParkingSpotNotificationRepository.findEmailAndParkingSpotId(
            availableParkingSpotNotificationRequest.parkingSpotId,
            availableParkingSpotNotificationRequest.email,
        )

        if (notificationCreated.isNotEmpty()) {
            println("Already exists a notification appointed.")
        } else {
            availableParkingSpotNotificationRepository.save(
                AvailableParkingSpotNotification(
                    parkingSpotId = availableParkingSpotNotificationRequest.parkingSpotId,
                    email = availableParkingSpotNotificationRequest.email,
                )
            )
        }

    }
}