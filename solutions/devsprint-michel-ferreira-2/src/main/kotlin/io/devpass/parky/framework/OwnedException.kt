package io.devpass.parky.framework

open class OwnedException(message: String) : Exception(message)
class VehicleException(message: String) : OwnedException(message)
class CheckInException(message: String) : OwnedException(message)
class CheckOutException(message: String) : OwnedException(message)
class NotFoundException(message: String) : OwnedException(message)