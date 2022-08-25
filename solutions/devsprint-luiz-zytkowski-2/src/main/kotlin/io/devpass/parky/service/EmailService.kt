package io.devpass.parky.service

import io.devpass.parky.entity.ParkingSpot
import org.springframework.stereotype.Service
import kotlin.concurrent.thread

@Service
class EmailService(
) {
    fun sendEmail(emailList: List<String>, parkingSpot: ParkingSpot) {
        emailList.ifEmpty {
            println("Serviço de e-mail está funcionando, porém a lista de endereços está vazia")
            return
        }

        thread {
            Thread.sleep(2_500)

            emailList.forEach {
                println("E-mail enviado para: $it")
                println("Vaga $parkingSpot liberada")
            }
        }
    }
}