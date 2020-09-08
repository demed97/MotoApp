package com.android.dan.motoapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dan.motoapp.entities.Login
import com.android.dan.motoapp.repository.LoginRepository
import com.android.dan.motoapp.utils.Result
import kotlinx.coroutines.*
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

    fun loginClick() {
//        val login = Login(email.value!!, password.value!!)
        scope.launch {
            loginUser(login.value!!)
        }
    }

    private suspend fun loginUser(value: Login) {
        val result = loginRepository.apiLogin(value)
        when (result) {
            Result.SuccessResult(Unit) -> {
                loginRepository.isRememberLogin(login.value!!, check)
                Log.d("success", "${login.value!!.username}, ${login.value!!.password}")
                _chooseActivityLiveData.postValue(Unit)
            }
            else -> Log.d("TAG", (result as Result.ExceptionResult).exception)
        }
    }

    fun checkAuthorization() {
        scope.launch {
           val result = loginRepository.checkAuthorization()
            when (result) {
                is Result.SuccessResult<*> -> {
                    result.result as Login
                    Log.d("check", "do ${result.result.username}, ${result.result.password}")
//                    putLogin(result.result)
                    login.postValue(result.result)
                    Log.d("check", "posle ${login.value!!.username}, ${login.value!!.password}")
                    check = true
                    loginUser(result.result)
                }
                else -> {
                    Log.d("check", (result as Result.ExceptionResult).exception)
                }
            }
        }
    }

    fun onEnabled() {
        check = true
        Log.d("check", "$check")
    }

    fun onDisabled() {
        check = false
        Log.d("check", "$check")
    }

}