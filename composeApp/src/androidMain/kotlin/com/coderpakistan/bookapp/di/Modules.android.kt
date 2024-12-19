package com.coderpakistan.bookapp.di

import com.coderpakistan.bookapp.book.data.database.DatabaseFactory
import io.ktor.client.engine.HttpClientEngine
import org.koin.core.module.Module
import org.koin.dsl.module
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidApplication

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine>{
            OkHttp.create()
        }
        single {
            DatabaseFactory(androidApplication())
        }
    }