package com.cypress.cyvideoplayer.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cypress.cyvideoplayer.repositories.VideoItem
import com.cypress.cyvideoplayer.viewModels.VideoViewModel

@Composable
actual fun VideoListScreen(
    onNavigation : (VideoItem) -> Unit
) {
}