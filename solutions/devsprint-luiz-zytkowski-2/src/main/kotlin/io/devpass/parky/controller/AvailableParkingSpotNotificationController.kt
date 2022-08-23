package io.devpass.parky.controller

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.repository.AvailableParkingSpotNotificationRepository
import io.devpass.parky.requests.AvailableParkingSpotNotificationRequest
import io.devpass.parky.service.AvailableParkingSpotNotificationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/available-parking-spot")
class AvailableParkingSpotNotificationController(
    private val availableParkingSpotNotificationService: AvailableParkingSpotNotificationService,
){
    //criar endpoint para o usuario criar notificaçao
    //post

    @PostMapping
    fun createNotification(
        @RequestBody availableParkingSpotNotificationRequest: AvailableParkingSpotNotificationRequest
    ) =
        availableParkingSpotNotificationService.createNotification(availableParkingSpotNotificationRequest)
}