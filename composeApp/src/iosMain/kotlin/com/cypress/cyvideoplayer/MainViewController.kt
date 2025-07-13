package com.cypress.cyvideoplayer

import androidx.compose.ui.window.ComposeUIViewController
import com.cypress.cyvideoplayer.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}