package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import io.devpass.parky.framework.OwnedException
import org.springframework.stereotype.Service
import kotlin.concurrent.thread

@Service
class EmailService(
) {
    fun sendEmail(emailList: List<String>, parkingSpot: ParkingSpot?) {
        thread {
            if (parkingSpot == null) {
                throw OwnedException("Impossível enviar e-mail, pois a vaga informada é nula")
            }

            emailList.forEach {
                println("E-mail enviado para: $it")
                println("Vaga $parkingSpot liberada")
            }
        }

    }
}