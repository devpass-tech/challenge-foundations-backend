package io.devpass.parky.service

import io.devpass.parky.entity.AvailableParkingSpotNotification
import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.exceptions.NotificationException
import io.devpass.parky.repository.AvailableParkingSpotNotificationRepository
import io.devpass.parky.requests.AvailableParkingSpotNotificationRequest
import org.springframework.stereotype.Service

@Service
class AvailableParkingSpotNotificationService(
    private val availableParkingSpotNotificationRepository: AvailableParkingSpotNotificationRepository,
    private val emailService: EmailService,
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

    fun checkOutNotification(parkingSpot: ParkingSpot) {
        val availableParkingSpotNotification =
            availableParkingSpotNotificationRepository.findByParkingSpotId(parkingSpot.id).ifEmpty {
                println("Nenhuma pessoa precisou ser notificada")
                return
            }

        emailService.sendEmail(
            findEmailsNotificationsByParkingSpotId(parkingSpot.id),
            parkingSpot
        )

        availableParkingSpotNotification.forEach { deleteNotification(it) }
    }

    fun findEmailsNotificationsByParkingSpotId(parkingSpotId: Int): List<String> =
        availableParkingSpotNotificationRepository.findByParkingSpotId(parkingSpotId).map { it.email }

    fun deleteNotification(availableParkingSpotNotification: AvailableParkingSpotNotification) {
        availableParkingSpotNotificationRepository.delete(availableParkingSpotNotification)
    }
}