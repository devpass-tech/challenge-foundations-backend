package io.devpass.parky.provider

import io.devpass.parky.provider.request.PaymentCheckOutRequest
import io.devpass.parky.provider.response.PaymentCheckOutResponse

interface CheckoutPayment {

    fun calculateCheckOut(paymentCheckOutRequest: PaymentCheckOutRequest): PaymentCheckOutResponse
}