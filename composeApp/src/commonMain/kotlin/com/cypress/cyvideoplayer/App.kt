package com.cypress.cyvideoplayer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cypress.cyvideoplayer.composables.VideoPlayer
import com.cypress.cyvideoplayer.viewModels.VideoViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import cypressvideoplayer.composeapp.generated.resources.Res
import cypressvideoplayer.composeapp.generated.resources.compose_multiplatform
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.KoinContext

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel: VideoViewModel = koinViewModel()
        VideoPlayer("", modifier = Modifier.fillMaxWidth().height(250.dp) , viewModel )
    }
}