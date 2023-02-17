package io.devpass.parky

import com.fasterxml.jackson.databind.json.JsonMapper
import io.devpass.parky.provider.ParkyPaymentClient
import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

@SpringBootApplication
@EnableFeignClients(basePackageClasses = [ParkyPaymentClient::class], clients = [ParkyPaymentClient::class])
@EnableJpaRepositories(basePackageClasses = [ParkingSpotRepository::class])
class ParkyApplication

fun main(args: Array<String>) {
	runApplication<ParkyApplication>(*args)

}
