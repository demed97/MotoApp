package com.android.dan.motoapp

import android.app.Activity
import android.app.Application
import com.android.dan.motoapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class App : DaggerApplication() {

//    @Inject
//    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

//    override fun onCreate() {
//        super.onCreate()
////        DaggerAppComponent.builder().application(this).build().inject(this)
//    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

//    override fun activityInjector(): AndroidInjector<Activity> {
//        return dispatchingAndroidInjector
//    }
}