package com.cypress.cyvideoplayer

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.cypress.cyvideoplayer.composables.VideoListScreen
import com.cypress.cyvideoplayer.composables.VideoPlayerScreen
import com.cypress.cyvideoplayer.repositories.VideoItem
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.annotation.KoinExperimentalAPI

sealed class Screen {
    object VideoList : Screen()
    data class VideoPlayer(val videoItem: VideoItem) : Screen()
}

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {

        var currentScreen by remember { mutableStateOf<Screen>(Screen.VideoList) }

        when (val screen = currentScreen) {
            is Screen.VideoList -> VideoListScreen(onNavigation = { videoItem ->
                currentScreen = Screen.VideoPlayer(videoItem)
            })
            is Screen.VideoPlayer -> {
                VideoPlayerScreen(videoItem = screen.videoItem , {
                    currentScreen = Screen.VideoList
                })
            }
        }

    }
}