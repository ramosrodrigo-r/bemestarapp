package br.com.rodrigo.bemestarapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.rodrigo.bemestarapp.databinding.FragmentSecondBinding
import br.com.rodrigo.bemestarapp.presentation.viewmodel.CheckInViewModel
import br.com.rodrigo.bemestarapp.presentation.viewmodel.CheckInViewModelFactory
import br.com.rodrigo.bemestarapp.data.local.AppDatabase
import br.com.rodrigo.bemestarapp.data.remote.RetrofitClient
import br.com.rodrigo.bemestarapp.domain.repository.CheckInRepository
import android.util.Log




class SecondFragment : Fragment() {

    private lateinit var viewModel: CheckInViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = CheckInRepository(
            AppDatabase.getInstance(requireContext()).checkInDao(),
            RetrofitClient.api
        )
        viewModel = ViewModelProvider(this, CheckInViewModelFactory(repository))[CheckInViewModel::class.java]

        viewModel.loadWeeklyData()

        viewModel.weeklyData.observe(viewLifecycleOwner) { checkIns ->
            // Aqui você pode alimentar um gráfico com MPAndroidChart ou outro componente de visualização
            for (checkIn in checkIns) {
                Log.d("CheckIn", "Data: ${checkIn.date}, Humor: ${checkIn.mood}")
            }
        }
    }
}
