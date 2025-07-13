package com.cypress.cyvideoplayer.di

import androidx.media3.exoplayer.ExoPlayer
import com.cypress.cyvideoplayer.repositories.VideoListRepository
import com.cypress.cyvideoplayer.repositories.VideoListRepositoryImp
import com.cypress.cyvideoplayer.repositories.VideoPlayerRepository
import com.cypress.cyvideoplayer.repositories.VideoPlayerRepositoryImp
import com.cypress.cyvideoplayer.viewModels.VideoListViewModel
import com.cypress.cyvideoplayer.viewModels.VideoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule = module{

    singleOf(::VideoListRepositoryImp).bind<VideoListRepository>()
    factoryOf(::VideoPlayerRepositoryImp).bind<VideoPlayerRepository>() // new instance every time it's injected
    factory { ExoPlayer.Builder(androidContext()).build() } // new instance every time it's injected
    viewModel { VideoViewModel(get()) }
    viewModel { VideoListViewModel(get()) }

}