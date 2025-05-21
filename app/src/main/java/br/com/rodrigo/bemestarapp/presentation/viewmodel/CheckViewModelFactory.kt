package br.com.rodrigo.bemestarapp.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.rodrigo.bemestarapp.data.local.AppDatabase
import br.com.rodrigo.bemestarapp.data.remote.RetrofitClient
import br.com.rodrigo.bemestarapp.domain.repository.CheckRepository

class CheckViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckViewModel::class.java)) {
            val db = AppDatabase.getInstance(context)
            val dao = db.checkInDao()
            val api = RetrofitClient.api
            val repository = CheckRepository(dao, api)

            return CheckViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
