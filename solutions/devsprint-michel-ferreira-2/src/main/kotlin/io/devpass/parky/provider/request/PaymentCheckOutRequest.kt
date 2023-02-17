package io.devpass.parky.provider.request

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PaymentCheckOutRequest(
    @JsonProperty("check_in_time")
    val checkInTime: String,
    @JsonProperty("check_out_time")
    val checkOutTime: String
)