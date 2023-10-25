package com.cmed.characters.View.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

import androidx.fragment.app.viewModels

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cmed.characters.R
import com.cmed.characters.Services.Model.responseDataItem
import com.cmed.characters.Utils.NetworkResult
import com.cmed.characters.View.Adapter.HarryPotterAdapter
import com.cmed.characters.View.Adapter.LoaderAdapter
import com.cmed.characters.ViewModel.HarrayPotterViewModel
import com.cmed.characters.databinding.FragmentHarryPotterHomeBinding
import com.google.gson.Gson

import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
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
        _binding = FragmentHarryPotterHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HarryPotterAdapter(this::onHPClicked)
        //harrayPotterViewModel.getHPCharacter()




        binding.hpuserlist.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.hpuserlist.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )


        bindObservers()
    }


    private fun bindObservers() {
       /*
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
        */

        harrayPotterViewModel.list.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle,it)
        })
    }

    private fun onHPClicked(responseDataItem: responseDataItem){
        val bundle = Bundle()
        bundle.putString("HP", Gson().toJson(responseDataItem))
        findNavController().navigate(R.id.action_charactersHome_to_harryPotterDetails, bundle)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}