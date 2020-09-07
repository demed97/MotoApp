package com.android.dan.motoapp.di.modules

import com.android.dan.motoapp.ui.moto.MotoAdapter
import dagger.Module
import dagger.Provides

@Module
class RecyclerModule {

    @Provides
    fun getAdapter() : MotoAdapter{
        return MotoAdapter(listOf())
    }
}