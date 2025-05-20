package br.com.rodrigo.bemestarapp.domain.repository

import br.com.rodrigo.bemestarapp.data.local.CheckInDao
import br.com.rodrigo.bemestarapp.data.remote.CheckInApi
import br.com.rodrigo.bemestarapp.util.toDomain
import br.com.rodrigo.bemestarapp.util.toEntity
import br.com.rodrigo.bemestarapp.domain.model.CheckIn
import br.com.rodrigo.bemestarapp.domain.model.Tip

class CheckInRepository(
    private val dao: CheckInDao,
    private val api: CheckInApi
) {
    suspend fun insertCheckIn(checkIn: CheckIn) {
        dao.insert(checkIn.toEntity())
    }

    suspend fun getWeeklyCheckIns(): List<CheckIn> =
        dao.getWeeklyCheckIns().map { it.toDomain() }

    suspend fun checkForNegativeStreak(): Boolean =
        dao.getLastThreeCheckIns().all { it.mood <= 1 }

    suspend fun getTips(): List<Tip> = api.getTips()
    suspend fun getSupportMessage(): Tip = api.getSupportMessage()
}
