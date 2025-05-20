package br.com.rodrigo.bemestarapp.util

import br.com.rodrigo.bemestarapp.data.local.CheckInEntity
import br.com.rodrigo.bemestarapp.domain.model.CheckIn

fun CheckIn.toEntity() = CheckInEntity(id, date, mood, note, motivation, focus, support)
fun CheckInEntity.toDomain() = CheckIn(id, date, mood, note, motivation, focus, support)
