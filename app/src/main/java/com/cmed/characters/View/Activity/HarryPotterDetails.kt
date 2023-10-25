package com.cmed.characters.View.Activity

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cmed.characters.R
import com.cmed.characters.databinding.FragmentHarryPotterDetailsBinding
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cmed.characters.Services.Model.responseDataItem
import com.cmed.characters.ViewModel.HarrayPotterViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagingApi
@AndroidEntryPoint
class HarryPotterDetails : Fragment() {
   private var _binding: FragmentHarryPotterDetailsBinding? = null
    private val binding   get() = _binding

    private var HPDetails: responseDataItem? =  null
    private  val harrayPotterViewModel by viewModels<HarrayPotterViewModel> (  )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHarryPotterDetailsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val selected = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.slected))
        val default = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.regular))


        binding?.actor?.setOnClickListener {
            binding!!.personalinfoconnection.visibility = View.VISIBLE
            binding!!.moviewViewconnection.visibility = View.GONE
            ViewCompat.setBackgroundTintList( binding!!.actor, selected)
            ViewCompat.setBackgroundTintList(  binding!!.movie, default)


        }

        binding?.movie?.setOnClickListener {
            ViewCompat.setBackgroundTintList( binding!!.actor, default)
            ViewCompat.setBackgroundTintList(  binding!!.movie, selected)
            binding!!.moviewViewconnection.visibility = View.VISIBLE
            binding!!.personalinfoconnection.visibility = View.GONE

        }

        binding?.imageView?.setOnClickListener {
            findNavController().navigate(R.id.action_harryPotterDetails_to_charactersHome)
        }

        setData()

    }

    private fun setData() {
       val jsonDetails = arguments?.getString("HP")

        if(jsonDetails != null){
            HPDetails = Gson().fromJson(jsonDetails,responseDataItem::class.java)

            HPDetails.let {

                    binding?.profileImage?.let { it1 ->
                        Glide.with(this)
                            .load(it?.image)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.profile)
                            .error(R.drawable.profile)
                            .into(it1)
                    }

                binding?.textView3?.text = it?.name ?: ""
                binding?.actorname?.text = it?.actor ?: ""
//                    binding?.altrrName?.text = it?.alternate_names?.get(0) ?: ""
                val alternateNames = it?.alternate_names

                if (alternateNames != null && alternateNames.isNotEmpty()) {
                    binding?.altrrName?.text = alternateNames[0]
                } else {
                    binding?.altrrName?.text = ""
                }

                binding?.gender?.text = it?.gender ?: ""
                binding?.dob?.text = it?.dateOfBirth ?: ""
                binding?.yob?.text = it?.yearOfBirth.toString() ?: ""
                binding?.ec?.text = it?.eyeColour ?: ""
                binding?.hr?.text = it?.hairColour ?: ""
                binding?.wood?.text = it?.wand?.wood ?: ""
                binding?.length?.text = it?.wand?.length.toString() ?: ""
                binding?.core?.text = it?.wand?.core ?: ""
                binding?.specis?.text = it?.species ?: ""
                binding?.wizerd?.text = it?.wizard.toString() ?: ""
                binding?.ancestry?.text = it?.ancestry ?: ""
                binding?.patronus?.text = it?.patronus ?: ""
                binding?.hs?.text = it?.hogwartsStudent.toString() ?: ""


            }

        }
    }


}