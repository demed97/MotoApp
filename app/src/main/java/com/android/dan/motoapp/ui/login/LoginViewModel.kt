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

    var email = MutableLiveData<String>()
    var password = MutableLiveData<String>()

    private var _chooseActivityLiveData = MutableLiveData<Unit>()
    val chooseActivityLiveData: LiveData<Unit> = _chooseActivityLiveData

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("TAG", "CoroutineExceptionHandler got", exception)
    }

    private val scope = viewModelScope + handler + Dispatchers.Default

    fun login() {
        val login = Login(email.value!!, password.value!!)
        scope.launch {
            val result = loginRepository.apiLogin(login)
            when (result) {
                Result.SuccessResult(Unit) -> _chooseActivityLiveData.postValue(Unit)
                else -> Log.d("TAG", (result as Result.ExceptionResult).exception )
            }
        }
    }

}