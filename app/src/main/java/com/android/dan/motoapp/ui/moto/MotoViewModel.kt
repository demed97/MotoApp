package com.android.dan.motoapp.ui.moto

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.dan.motoapp.entities.Moto
import com.android.dan.motoapp.repository.LoginRepository
import com.android.dan.motoapp.repository.MotoRepository
import com.android.dan.motoapp.utils.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

class MotoViewModel @Inject constructor(
    private val motoRepository: MotoRepository,
    private val loginRepository: LoginRepository
) : ViewModel() {


    private var _motoLiveData = MutableLiveData<List<Moto>>()
    val motoLiveData: LiveData<List<Moto>> = _motoLiveData

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("TAG", "CoroutineExceptionHandler got", exception)
    }

    private val scope = viewModelScope + handler + Dispatchers.Default

    init {
        getMotoList()
    }

    fun getMotoList() {
        scope.launch {
            val result = motoRepository.getMoto()
            when (result) {
                is Result.SuccessResult<*> -> _motoLiveData.postValue(result.result as List<Moto>)
                is Result.ExceptionResult -> Log.d("TAG", result.exception)
            }
        }
    }

    fun deleteLogin(){
        scope.launch {
            loginRepository.deleteLogin()
        }
    }
}