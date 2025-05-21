package br.com.rodrigo.bemestarapp.domain.model

data class CheckIn(
    val id: Int = 0,
    val date: String,
    val mood: Int,
    val note: String?,
    val motivation: Int,
    val focus: Int,
    val support: Int,
)
