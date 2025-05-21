package br.com.rodrigo.bemestarapp.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CheckInViewModelFactory(
    private val repository: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckInViewModel::class.java)) {
            return CheckInViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
