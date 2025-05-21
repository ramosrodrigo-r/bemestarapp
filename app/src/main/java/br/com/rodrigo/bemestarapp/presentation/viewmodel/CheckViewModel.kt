package br.com.rodrigo.bemestarapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rodrigo.bemestarapp.domain.model.CheckIn
import br.com.rodrigo.bemestarapp.domain.model.Tip
import br.com.rodrigo.bemestarapp.domain.repository.CheckInRepository
import kotlinx.coroutines.launch

class CheckInViewModel(
    private val repository: CheckInRepository
) : ViewModel() {

    // Dados da semana
    private val _weeklyData = MutableLiveData<List<CheckIn>>()
    val weeklyData: LiveData<List<CheckIn>> get() = _weeklyData

    // Mensagem de apoio emocional
    private val _tip = MutableLiveData<Tip?>()
    val tip: LiveData<Tip?> get() = _tip

    // Insere check-in e verifica padrão emocional negativo
    fun insertCheckIn(checkIn: CheckIn) {
        viewModelScope.launch {
            repository.insertCheckIn(checkIn)
            if (repository.checkForNegativeStreak()) {
                _tip.value = repository.getSupportMessage()
            }
        }
    }

    // Carrega os check-ins da semana
    fun loadWeeklyData() {
        viewModelScope.launch {
            _weeklyData.value = repository.getWeeklyCheckIns()
        }
    }
}
