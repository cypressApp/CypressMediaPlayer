package com.cypress.cyvideoplayer.composables

import android.Manifest
import android.net.Uri
import android.os.Build
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import com.cypress.cyvideoplayer.viewModels.VideoListViewModel
import com.cypress.cyvideoplayer.viewModels.VideoViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.compose.viewmodel.koinViewModel
import com.cypress.cyvideoplayer.repositories.VideoItem
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
actual fun VideoListScreen(onNavigation : (VideoItem) -> Unit) {

    val videoListViewModel : VideoListViewModel = koinViewModel()
    val videoList by videoListViewModel.videoList.collectAsState()

//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent(),
//        onResult = { uri : Uri? ->
//            videoPlayerViewModel.play(uri)
//        }
//    )

//    DisposableEffect(Unit) {
//        onDispose {
//            viewModel.release()
//        }
//    }

    Column(modifier = Modifier
        .safeContentPadding()
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

//        Button(onClick = {
//            launcher.launch("video/*")
//        }) {
//            Text("Open Video")
//        }

        LazyColumn {
            items(videoList){ videoItem ->

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable{
                        onNavigation(videoItem)
                       // videoPlayerViewModel.play(videoItem.uri.toUri())
                    } ,
                    verticalAlignment = Alignment.CenterVertically) {
                    videoItem.thumbnail?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.size(52.dp)
                                .clip(RoundedCornerShape(16.dp)), // Rounded corners
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = videoItem.title,
                        modifier = Modifier.weight(1f),
                        fontSize = 12.sp
                    )
                }
            }
        }

    }

    CheckVideoPermission()

}

@OptIn(ExperimentalPermissionsApi::class, KoinExperimentalAPI::class)
@Composable
fun CheckVideoPermission() {

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
