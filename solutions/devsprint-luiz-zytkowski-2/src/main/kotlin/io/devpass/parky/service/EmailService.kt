package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import org.springframework.stereotype.Service
import kotlin.concurrent.thread

@Service
class EmailService(
) {
    fun sendEmail(emailList: List<String>, parkingSpot: ParkingSpot) {
        thread {
            Thread.sleep(2_500)

            emailList.forEach {
                println("E-mail enviado para: $it")
                println("Vaga $parkingSpot liberada")
            }
        }

    }
}