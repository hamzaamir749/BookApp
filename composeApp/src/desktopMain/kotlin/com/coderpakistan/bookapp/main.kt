package com.coderpakistan.bookapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.coderpakistan.bookapp.app.App
import com.coderpakistan.bookapp.di.initKoin

fun main(){
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "BookApp",
        ) {
            App()
        }
    }
}