package com.android.dan.motoapp.ui.login

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.dan.motoapp.R
import com.android.dan.motoapp.databinding.ActivityMainBinding
import com.android.dan.motoapp.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        checkConnection()
        observeToLiveData()

        val mainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mainBinding.loginViewModel = loginViewModel
    }

    private fun observeToLiveData() {
        loginViewModel.chooseActivityLiveData.observe(this, Observer {
            showSnackbar()
        })
    }

    private fun showSnackbar() {
        Snackbar.make(
            loginConstLayout,
            "OK",
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction("OK", View.OnClickListener { })
            .show()
    }


    private fun checkConnection() {
        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .build()
        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Toast.makeText(baseContext, "Network is available", Toast.LENGTH_LONG).show()
            }

            override fun onLost(network: Network) {
                Toast.makeText(baseContext, "Network was lost", Toast.LENGTH_LONG).show()
            }
        }

        try {
            manager.unregisterNetworkCallback(callback)
        } catch (e: Exception) {
            Log.w(
                "MainActivity",
                "NetworkCallback for Wi-fi was not registered or already unregistered"
            )
        }
        manager.registerNetworkCallback(networkRequest, callback)
    }
}