package io.devpass.parky.controller

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.service.ParkingSpotService
import net.bytebuddy.asm.Advice
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/free-all-parking-spots")

class AdminController(
        private val parkingSpotService: ParkingSpotService
) {
    @GetMapping
    fun getAllParkingSpots() {
        val listOfParkingSpots = parkingSpotService.findAll()
        listOfParkingSpots.forEach()
        {
            if (it.inUseBy == null) {
                println("Não precisei limpar a vaga ${it.id} pois não estava em uso")
                return@forEach
            }
            println(it.toString())
            return listOfParkingSpots
        }
    }
}






