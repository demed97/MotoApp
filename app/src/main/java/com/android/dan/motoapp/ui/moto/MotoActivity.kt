package com.android.dan.motoapp.ui.moto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.dan.motoapp.R
import com.android.dan.motoapp.databinding.ActivityMotoBinding
import com.android.dan.motoapp.ui.login.MainActivity
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



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exit_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        motoViewModel.deleteLogin()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        return super.onOptionsItemSelected(item)
    }
}