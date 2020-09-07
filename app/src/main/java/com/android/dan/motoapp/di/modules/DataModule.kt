package com.android.dan.motoapp.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.android.dan.motoapp.api.Api
import com.android.dan.motoapp.api.Controller
import com.android.dan.motoapp.api.HeaderInterceptor
import com.android.dan.motoapp.database.moto.MotoDao
import com.android.dan.motoapp.database.moto.MotoDatabase
import com.android.dan.motoapp.repository.LoginRepository
import com.android.dan.motoapp.repository.MotoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun getApplication(application: Application): Context = application

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

    @Singleton
    @Provides
    fun getDatabase(application: Application): MotoDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            MotoDatabase::class.java,
            "book_database"
        ).build()
    }

    @Singleton
    @Provides
    fun getDatabaseDao(motoDatabase: MotoDatabase): MotoDao {
        return motoDatabase.getMotoDao()
    }

    @Singleton
    @Provides
    fun getMotoRepository(api : Api, motoDao: MotoDao) : MotoRepository{
        return MotoRepository(api, motoDao)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context) : SharedPreferences {
        return  PreferenceManager.getDefaultSharedPreferences(context)
    }

}