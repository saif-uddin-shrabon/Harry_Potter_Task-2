package com.cmed.characters.View.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cmed.characters.R
import com.cmed.characters.Utils.NetworkResult
import com.cmed.characters.View.Adapter.HarryPotterAdapter
import com.cmed.characters.ViewModel.HarrayPotterViewModel
import com.cmed.characters.databinding.FragmentHarryPotterHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HarryPotterHome : Fragment() {

   private var _binding: FragmentHarryPotterHomeBinding? = null
    private val binding get() = _binding!!
    private  val harrayPotterViewModel by viewModels<HarrayPotterViewModel> (  )

    private  lateinit var adapter: HarryPotterAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHarryPotterHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        harrayPotterViewModel.getHPCharacter()
//        binding.hpuserlist.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
//        binding.hpuserlist.adapter = adapter
//
//
//        bindObservers()
    }

    private fun bindObservers() {

        harrayPotterViewModel.potterLiveData.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is NetworkResult.Success -> {
                    val reponse = result.data
                    println("OvserverResponse: ${reponse.toString()}")
                    adapter.submitList(reponse)
                }
                is NetworkResult.Error ->{
                    Toast.makeText(requireContext(), result.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    println("Loading............")
                }
            }

        })

    }


}