package br.com.rodrigo.bemestarapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.bemestarapp.R
import br.com.rodrigo.bemestarapp.databinding.FragmentCheckBinding
import br.com.rodrigo.bemestarapp.domain.model.Check
import br.com.rodrigo.bemestarapp.presentation.viewmodel.CheckViewModel
import br.com.rodrigo.bemestarapp.presentation.viewmodel.CheckViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class CheckFragment : Fragment() {

    private var _binding: FragmentCheckBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CheckViewModel by viewModels {
        CheckViewModelFactory(requireContext())
    }

    private var selectedMood: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupMoodButtons()
        setupListeners()
    }

    private fun setupMoodButtons() {
        val moodButtons = listOf(
            binding.btnMood1,
            binding.btnMood2,
            binding.btnMood3,
            binding.btnMood4,
            binding.btnMood5
        )

        moodButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                selectedMood = index + 1
                Toast.makeText(requireContext(), "Humor selecionado: $selectedMood", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupListeners() {
        binding.btnSaveCheckIn.setOnClickListener {
            if (selectedMood == 0) {
                Toast.makeText(requireContext(), "Selecione um humor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val note = binding.editObservation.text.toString()
            val motivation = binding.seekMotivation.progress
            val focus = binding.seekFocus.progress
            val support = binding.seekSupport.progress
            val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            val check = Check(
                id = 0,
                date = currentDate,
                mood = selectedMood,
                note = note,
                motivation = motivation,
                focus = focus,
                support = support
            )

            viewModel.insertCheckIn(check)
            Toast.makeText(requireContext(), "Check-in salvo com sucesso!", Toast.LENGTH_SHORT).show()
        }

        binding.btnGoToTips.setOnClickListener {
            findNavController().navigate(R.id.action_checkInFragment_to_tipFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
