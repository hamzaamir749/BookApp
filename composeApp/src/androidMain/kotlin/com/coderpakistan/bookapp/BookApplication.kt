package com.coderpakistan.bookapp

import android.app.Application
import com.coderpakistan.bookapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class BookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@BookApplication)
        }
    }
}