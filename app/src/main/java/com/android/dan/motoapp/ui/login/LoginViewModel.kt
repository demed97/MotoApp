package com.android.dan.motoapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dan.motoapp.entities.Login
import com.android.dan.motoapp.repository.LoginRepository
import com.android.dan.motoapp.utils.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    var login = MutableLiveData<Login>(Login("", ""))
//    var password = MutableLiveData<String>()

    private var _chooseActivityLiveData = MutableLiveData<Unit>()
    val chooseActivityLiveData: LiveData<Unit> = _chooseActivityLiveData

//    private var _checkLiveData = MutableLiveData<Boolean>()
//    val checkLiveData: LiveData<Boolean> = _checkLiveData

    var check = false

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("TAG", "CoroutineExceptionHandler got", exception)
    }

    private val scope = viewModelScope + handler + Dispatchers.Default

    fun login() {
//        val login = Login(email.value!!, password.value!!)
        scope.launch {
            val result = loginRepository.apiLogin(login.value!!)
            when (result) {
                Result.SuccessResult(Unit) -> {
                    loginRepository.isRememberLogin(login.value!!, check)
                    Log.d("TAG", "${login.value!!.username}, ${login.value!!.password}")
                    _chooseActivityLiveData.postValue(Unit)
                }
                else -> Log.d("TAG", (result as Result.ExceptionResult).exception)
            }
        }
    }

    fun checkAuthorization() {
        scope.launch {
            val result = loginRepository.checkAuthorization()
            when (result) {
                is Result.SuccessResult<*> -> {
                    result.result as Login
                    Log.d("TAG", "do ${login.value!!.username}, ${login.value!!.password}")
                    login.postValue(result.result)
                    Log.d("TAG", "posle ${login.value!!.username}, ${login.value!!.password}")
                    check = true
                    login()
                }
                else -> Log.d("TAG", (result as Result.ExceptionResult).exception)
            }
        }
    }

//    private fun checkRemember() {
//        if (check) {
//
//        }
//        _checkLiveData.postValue(check)
//    }

    fun onEnabled() {
        check = true
    }

    fun onDisabled() {
        check = false
    }

}