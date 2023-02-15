package io.devpass.parky.repository

import io.devpass.parky.entity.ParkingSpot
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParkingSpotRepository : CrudRepository<ParkingSpot, Int> {
    fun findByFloorAndSpot(floor: Int, spot: Int): ParkingSpot?
    fun findByInUseBy(inUseBy: String): ParkingSpot?

    //TODO validar se faz sentido usar, nao tinha visto que ja tinha uma validacao de duplicidade... CheckInOutService ln:33
    override fun <S : ParkingSpot> save(parkingSpot: S): S {
        if(parkingSpot.inUseBy?.let { findByInUseBy(it) } != null){
            return parkingSpot
        }
        return save(parkingSpot)
    }
}