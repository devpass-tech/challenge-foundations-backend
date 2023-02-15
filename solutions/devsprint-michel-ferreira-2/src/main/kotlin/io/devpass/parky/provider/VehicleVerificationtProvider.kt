package io.devpass.parky.provider

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.devpass.parky.framework.VehicleVerificationException
import io.devpass.parky.provider.response.VehicleVerificationResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class VehicleVerificationProvider(
    @Value("\${vehicle-verification.base-url}") private val verificationBaseUrl: String
) {
    fun verifyPLicensePlate(licensePlate: String): VehicleVerificationResponse {
        val fullPath = "$verificationBaseUrl/verify/$licensePlate"
        val mapper = ObjectMapper().registerKotlinModule()

        val response = RestTemplate().getForEntity(fullPath, String::class.java)

        if (response.statusCode == HttpStatus.OK) {
            return mapper.readValue(response.body, VehicleVerificationResponse::class.java)
        }

        throw VehicleVerificationException("${response.statusCode} error returned in request $fullPath")
    }
}