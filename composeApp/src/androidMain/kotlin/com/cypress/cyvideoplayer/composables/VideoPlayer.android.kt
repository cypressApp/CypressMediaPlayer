package com.cypress.cyvideoplayer.composables

import android.Manifest
import android.net.Uri
import android.os.Build
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import com.cypress.cyvideoplayer.viewModels.VideoListViewModel
import com.cypress.cyvideoplayer.viewModels.VideoViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.compose.viewmodel.koinViewModel
import androidx.core.net.toUri

@Composable
actual fun VideoPlayer(uri: String, modifier: Modifier , viewModel: VideoViewModel) {

    val context = LocalContext.current
    val videoListViewModel : VideoListViewModel = koinViewModel()
    val videoList by videoListViewModel.videoList.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri : Uri? ->
            viewModel.play(uri)
        }
    )

//    DisposableEffect(Unit) {
//        onDispose {
//            viewModel.release()
//        }
//    }

    Column(modifier = Modifier
        .safeContentPadding()
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Button(onClick = {
            launcher.launch("video/*")
        }) {
            Text("Open Video")
        }

        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = viewModel.exoPlayer
                    useController = true
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            modifier = modifier
        )

        LazyColumn {
            items(videoList){ videoItem ->

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable{
                        viewModel.play(videoItem.uriString.toUri())
                    }) {
                    Text(
                        text = videoItem.title ?: "Untitled",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

    }

    checkVideoPermission()

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun checkVideoPermission() {

    val videoListViewModel : VideoListViewModel = koinViewModel()

    val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_VIDEO
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val permissionState = rememberPermissionState(permission = permission)

    LaunchedEffect(Unit) {
        if (!permissionState.status.isGranted) {
            permissionState.launchPermissionRequest()
        } else {
            videoListViewModel.getAllList()
        }
    }

    if (permissionState.status.isGranted) {
        videoListViewModel.getAllList()
    } else {
        Text("Permission is required to view videos")
    }
}
