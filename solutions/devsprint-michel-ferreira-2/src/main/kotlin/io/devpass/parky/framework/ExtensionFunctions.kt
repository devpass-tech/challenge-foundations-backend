package io.devpass.parky.framework

import java.util.*

fun <T> Optional<T>.presentOrNull() : T? {
    return if (this.isPresent) {
        this.get()
    } else null
}