package br.com.rodrigo.bemestarapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rodrigo.bemestarapp.domain.model.Check
import br.com.rodrigo.bemestarapp.domain.model.Tip
import br.com.rodrigo.bemestarapp.domain.repository.CheckRepository
import kotlinx.coroutines.launch

class CheckViewModel(
    private val repository: CheckRepository
) : ViewModel() {

    // Dados da semana
    private val _weeklyData = MutableLiveData<List<Check>>()
    val weeklyData: LiveData<List<Check>> get() = _weeklyData

    // Mensagem de apoio emocional
    private val _tip = MutableLiveData<Tip?>()
    val tip: LiveData<Tip?> get() = _tip

    // Insere check-in e verifica padrão emocional negativo
    fun insertCheckIn(check: Check) {
        viewModelScope.launch {
            repository.insertCheckIn(check)
            if (repository.checkForNegativeStreak()) {
                _tip.value = repository.getSupportMessage()
            }
        }
    }

    fun loadTips() {
        viewModelScope.launch {
            try {
                val tips = repository.getTips()
                _tip.value = tips.firstOrNull()
            } catch (e: Exception) {
                e.printStackTrace()
                _tip.value = null
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
