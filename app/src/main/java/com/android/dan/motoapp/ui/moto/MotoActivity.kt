package com.android.dan.motoapp.ui.moto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.dan.motoapp.R
import com.android.dan.motoapp.databinding.ActivityMotoBinding
import com.android.dan.motoapp.utils.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MotoActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var adapter: MotoAdapter
    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var motoViewModel: MotoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moto)

        motoViewModel = ViewModelProvider(this, factory).get(MotoViewModel::class.java)
        motoViewModel.motoLiveData.observe(this, Observer {
            adapter.motoList = it
        })

        val activityMotoBinding = DataBindingUtil.setContentView<ActivityMotoBinding>(
            this,R.layout.activity_moto)
        activityMotoBinding.adapter = adapter
    }
}