package io.devpass.parky.framework

import java.util.Optional

fun <T> Optional<T>.getOrNull() = if (this.isPresent) this.get() else null