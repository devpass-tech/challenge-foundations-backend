package io.devpass.parky.framework

open class OwnedException(message: String) : Exception(message)
class CheckInException(message: String) : OwnedException(message)
class NotFoundException(message: String) : OwnedException(message)