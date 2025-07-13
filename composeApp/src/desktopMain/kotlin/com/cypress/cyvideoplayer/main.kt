package com.cypress.cyvideoplayer

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.cypress.cyvideoplayer.di.initKoin

fun main()  {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CypressVideoPlayer",
        ) {
            App()
        }
    }
}