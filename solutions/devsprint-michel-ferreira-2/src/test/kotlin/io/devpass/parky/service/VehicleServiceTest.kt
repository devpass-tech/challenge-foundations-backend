package io.devpass.parky.service

import io.devpass.parky.entity.Vehicle
import io.devpass.parky.framework.VehicleException
import io.devpass.parky.repository.VehicleRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class VehicleServiceTest {

    val vehicleRepository = mockk<VehicleRepository>()
    val parkingSpotEventService: ParkingSpotEventService = mockk()

    val vehicleService = VehicleService(
        vehicleRepository = vehicleRepository,
        parkingSpotEventService = parkingSpotEventService
    )

    @Test
    fun `Deve buscar todos os veiculos com sucesso`() {
        every { vehicleRepository.findAll() } returns listOf(getVehicle())

        val veiculos = vehicleService.findAll()

        Assertions.assertTrue(veiculos.isNotEmpty())
    }

    @Test
    fun `Deve buscar todos os veiculos e retornar vazio`() {
        every { vehicleRepository.findAll() } returns listOf()

        val veiculos = vehicleService.findAll()

        Assertions.assertTrue(veiculos.isEmpty())
    }

    @Test
    fun `Deve tentar salvar um Veiculo ja salvo e nao deve salvar o mesmo`() {
        every { vehicleRepository.findByLicensePlate(any()) } returns getVehicle()

       val vehicle = vehicleService.createIfNotExists(getVehicle())

        verify(exactly = 1) {
            vehicleRepository.findByLicensePlate(any())
        }

        verify(exactly = 0) {
            vehicleRepository.save(any())
        }

        Assertions.assertEquals(vehicle.licensePlate, getVehicle().licensePlate)
        Assertions.assertEquals(vehicle.id, getVehicle().id)
    }

    @Test
    fun `Deve tentar salvar um Veiculo inexistente e deve salvar o mesmo`() {
        val vehicleExpected = getVehicle()
        every { vehicleRepository.findByLicensePlate(any()) } returns null
        every { vehicleRepository.save(any()) } returns vehicleExpected

        val vehicle = vehicleService.createIfNotExists(getVehicle())

        verify(exactly = 2) {
            vehicleRepository.findByLicensePlate(any())
        }

        verify(exactly = 1) {
            vehicleRepository.save(any())
        }

        Assertions.assertEquals(vehicle.licensePlate, vehicleExpected.licensePlate)
        Assertions.assertEquals(vehicle.id, vehicleExpected.id)
    }

    @Test
    fun `Deve tentar salvar um Veiculo existente e deve lancar a excecao VehicleException`() {
        every { vehicleRepository.findByLicensePlate(any()) } returns getVehicle()

        val exception = assertThrows<VehicleException> {
            vehicleService.create(getVehicle())
        }

        verify(exactly = 1) {
            vehicleRepository.findByLicensePlate(any())
        }

        verify(exactly = 0) {
            vehicleRepository.save(any())
        }

        Assertions.assertEquals(exception.message, "This Vehicle is already insert in our Database. (license plate duplicated)")
    }

    private fun getVehicle(): Vehicle {
        return Vehicle(
            id = "123",
            brand = "",
            color = "",
            owner = "",
            licensePlate = "PFR-K3PS",
            createdAt = LocalDateTime.now()
        )
    }

}