package com.cypress.cyvideoplayer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cypress.cyvideoplayer.composables.VideoPlayer
import com.cypress.cyvideoplayer.viewModels.VideoListViewModel
import com.cypress.cyvideoplayer.viewModels.VideoViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val videoPlayerModel: VideoViewModel = koinViewModel()
        Column {
            VideoPlayer("", modifier = Modifier.fillMaxWidth().height(250.dp) , videoPlayerModel )
        }

    }
}