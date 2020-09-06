package com.android.dan.motoapp.di.modules

import com.android.dan.motoapp.ui.login.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}