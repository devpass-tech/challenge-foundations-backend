package io.devpass.parky.service

import io.devpass.parky.repository.AvailableParkingSpotNotificationRepository
import org.springframework.stereotype.Service

@Service
class AvailableParkingSpotNotificationService(
    private val availableParkingSpotNotificationRepository: AvailableParkingSpotNotificationRepository,
)