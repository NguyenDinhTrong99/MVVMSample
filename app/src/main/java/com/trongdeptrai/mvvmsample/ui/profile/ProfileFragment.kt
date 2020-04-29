package com.trongdeptrai.mvvmsample.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.trongdeptrai.mvvmsample.R
import com.trongdeptrai.mvvmsample.data.db.entities.User
import com.trongdeptrai.mvvmsample.databinding.FragmentProfileBinding
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

@Suppress("CAST_NEVER_SUCCEEDS")
class ProfileFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val factory:ProfileViewModelFactory by instance<ProfileViewModelFactory>()
    private lateinit var viewModel: ProfileViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProfileBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        viewModel = ViewModelProvider(this, factory).get(ProfileViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}
