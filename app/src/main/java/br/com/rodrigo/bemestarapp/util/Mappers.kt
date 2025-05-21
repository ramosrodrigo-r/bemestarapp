package br.com.rodrigo.bemestarapp.util

import br.com.rodrigo.bemestarapp.data.local.CheckEntity
import br.com.rodrigo.bemestarapp.domain.model.Check

fun Check.toEntity() = CheckEntity(id, date, mood, note, motivation, focus, support)
fun CheckEntity.toDomain() = Check(id, date, mood, note, motivation, focus, support)
