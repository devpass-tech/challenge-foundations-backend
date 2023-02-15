package io.devpass.parky

import io.devpass.parky.repository.ParkingSpotRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import javax.sql.DataSource

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = [ParkingSpotRepository::class])
class ParkyApplication

fun main(args: Array<String>) {
	runApplication<ParkyApplication>(*args)

}
