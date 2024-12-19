package com.coderpakistan.bookapp

import androidx.compose.ui.window.ComposeUIViewController
import com.coderpakistan.bookapp.app.App
import com.coderpakistan.bookapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}