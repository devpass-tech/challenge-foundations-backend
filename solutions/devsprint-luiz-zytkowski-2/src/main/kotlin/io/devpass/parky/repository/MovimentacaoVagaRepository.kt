package io.devpass.parky.repository

import io.devpass.parky.entity.MovimentacaoVaga
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MovimentacaoVagaRepository : CrudRepository<MovimentacaoVaga, Int> {
}