package br.com.rodrigo.bemestarapp.domain.repository

import br.com.rodrigo.bemestarapp.data.local.CheckDao
import br.com.rodrigo.bemestarapp.data.remote.CheckApi
import br.com.rodrigo.bemestarapp.util.toDomain
import br.com.rodrigo.bemestarapp.util.toEntity
import br.com.rodrigo.bemestarapp.domain.model.Check
import br.com.rodrigo.bemestarapp.domain.model.Tip

class CheckRepository(
    private val dao: CheckDao,
    private val api: CheckApi
) {
    suspend fun insertCheckIn(check: Check) {
        dao.insert(check.toEntity())
    }

    suspend fun getWeeklyCheckIns(): List<Check> =
        dao.getWeeklyCheckIns().map { it.toDomain() }

    suspend fun checkForNegativeStreak(): Boolean =
        dao.getLastThreeCheckIns().all { it.mood <= 1 }

    suspend fun getTips(): List<Tip> = api.getTips()
    suspend fun getSupportMessage(): Tip = api.getSupportMessage()
}
