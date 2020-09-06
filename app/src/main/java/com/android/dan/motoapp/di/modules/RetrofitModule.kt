package com.android.dan.motoapp.di.modules

import android.content.SharedPreferences
import com.android.dan.motoapp.api.Api
import com.android.dan.motoapp.api.Controller
import com.android.dan.motoapp.api.HeaderInterceptor
import com.android.dan.motoapp.repository.LoginRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun getController(headerInterceptor: HeaderInterceptor):Controller{
        return Controller(headerInterceptor)
    }

    @Singleton
    @Provides
    fun getApi(controller: Controller) : Api {
        return controller.createService()
    }

    @Singleton
    @Provides
    fun getLoginRepository(api : Api, sharedPreferencesModule: SharedPreferences) : LoginRepository{
        return LoginRepository(api,sharedPreferencesModule)
    }

}