package br.com.rodrigo.bemestarapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.rodrigo.bemestarapp.databinding.FragmentTipBinding
import br.com.rodrigo.bemestarapp.presentation.viewmodel.CheckViewModel
import br.com.rodrigo.bemestarapp.presentation.viewmodel.CheckViewModelFactory
import br.com.rodrigo.bemestarapp.R

class TipFragment : Fragment() {

    private var _binding: FragmentTipBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CheckViewModel by viewModels {
        CheckViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTipBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeTip()
        viewModel.loadTips() // <- troca aqui

        binding.btnBackToCheckIn.setOnClickListener {
            findNavController().navigate(R.id.action_tipFragment_to_checkInFragment)
        }
    }


    private fun observeTip() {
        viewModel.tip.observe(viewLifecycleOwner) { tip ->
            binding.tipMessage.text = tip?.message ?: "Nenhuma dica disponível no momento."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

