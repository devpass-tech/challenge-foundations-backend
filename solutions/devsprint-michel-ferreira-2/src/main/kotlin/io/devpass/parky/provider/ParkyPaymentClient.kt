package io.devpass.parky.provider

import io.devpass.parky.provider.request.PaymentCheckOutRequest
import io.devpass.parky.provider.response.PaymentCheckOutResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@FeignClient(value = "parky-payment", url = "http://localhost:8082")
interface ParkyPaymentClient : CheckoutPayment {

    @RequestMapping(method = [RequestMethod.POST], value = ["/check-out/calculate"], produces = ["application/json"])
    override fun calculateCheckOut(
        paymentCheckOutRequest: PaymentCheckOutRequest
    ): PaymentCheckOutResponse
}
