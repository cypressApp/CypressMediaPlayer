package com.cypress.cyvideoplayer.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cypress.cyvideoplayer.repositories.VideoItem


@Composable
expect fun VideoPlayerScreen(videoItem: VideoItem , onBackPressed: (String) -> Unit)