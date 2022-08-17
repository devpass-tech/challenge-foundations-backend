package io.devpass.parky.service

import io.devpass.parky.repository.MovimentacaoVagaRepository
import org.springframework.stereotype.Service

@Service
class VacancyMovimentationService(
    private val movimentacaoVagaRepository: MovimentacaoVagaRepository
) {
}