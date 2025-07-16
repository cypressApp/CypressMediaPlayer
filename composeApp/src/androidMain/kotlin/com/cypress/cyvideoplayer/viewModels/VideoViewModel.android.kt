package com.cypress.cyvideoplayer.viewModels

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.cypress.cyvideoplayer.repositories.VideoPlayerRepository

actual class VideoViewModel(val videoPlayerRepository: VideoPlayerRepository) : ViewModel(){

    val exoPlayer = videoPlayerRepository.exoPlayer

    fun release(){
        videoPlayerRepository.release()
    }

    fun stop(){
        videoPlayerRepository.stop()
    }

    fun play(uri: Uri?){
        videoPlayerRepository.play(uri)
    }

    override fun onCleared() {
        super.onCleared()
        videoPlayerRepository.release()
    }

}