package io.devpass.parky.service

import io.devpass.parky.entity.AvailableParkingSpotNotification
import io.devpass.parky.framework.OwnedException
import io.devpass.parky.repository.AvailableParkingSpotNotificationRepository
import org.springframework.stereotype.Service

@Service
class AvailableParkingSpotNotificationService(
    private val availableParkingSpotNotificationRepository: AvailableParkingSpotNotificationRepository,
) {
    fun checkOutNotification(parkingSpotId: Int) {
        val availableParkingSpotNotification =
            availableParkingSpotNotificationRepository.findByParkingSpotId(parkingSpotId).ifEmpty {
                throw OwnedException("Notificação não encontrada para a vaga $parkingSpotId!")
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