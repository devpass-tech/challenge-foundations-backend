package io.devpass.parky.service

import io.devpass.parky.entity.AvailableParkingSpotNotification
import io.devpass.parky.exceptions.NotificationException
import io.devpass.parky.repository.AvailableParkingSpotNotificationRepository
import io.devpass.parky.requests.AvailableParkingSpotNotificationRequest
import org.springframework.stereotype.Service

@Service
class AvailableParkingSpotNotificationService(
    private val availableParkingSpotNotificationRepository: AvailableParkingSpotNotificationRepository,
) {

    fun createNotification(availableParkingSpotNotificationRequest: AvailableParkingSpotNotificationRequest) {

        val notificationCreated = availableParkingSpotNotificationRepository.findEmailAndParkingSpotId(
            availableParkingSpotNotificationRequest.parkingSpotId,
            availableParkingSpotNotificationRequest.email,
        )

        if (notificationCreated.isNotEmpty()) {
            throw NotificationException("Already exists a notification appointed.")
        } else {
            availableParkingSpotNotificationRepository.save(
                AvailableParkingSpotNotification(
                    parkingSpotId = availableParkingSpotNotificationRequest.parkingSpotId,
                    email = availableParkingSpotNotificationRequest.email,
                )
            )
        }
    }

    fun checkOutNotification(parkingSpotId: Int) {
        val availableParkingSpotNotification =
            availableParkingSpotNotificationRepository.findByParkingSpotId(parkingSpotId).ifEmpty {
                println("Nenhuma pessoa precisou ser notificada")
                return
            }
        availableParkingSpotNotification.forEach {
            println("O email é ${it.email}")
            println("A vaga ${it.parkingSpotId} está disponível!")
            deleteNotification(it)
        }
    }

    fun deleteNotification(availableParkingSpotNotification: AvailableParkingSpotNotification) {
        availableParkingSpotNotificationRepository.delete(availableParkingSpotNotification)
    }

}