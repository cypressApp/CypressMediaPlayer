package com.cypress.cyvideoplayer.composables

import androidx.compose.runtime.Composable
import com.cypress.cyvideoplayer.repositories.VideoItem

@Composable
expect fun VideoListScreen(onNavigation : (VideoItem) -> Unit)