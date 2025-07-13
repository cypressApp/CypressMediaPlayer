package com.cypress.cyvideoplayer.repositories

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

actual interface VideoPlayerRepository{
    val exoPlayer: ExoPlayer
    fun release()
    fun play(uri : Uri?)

}

actual class VideoPlayerRepositoryImp(override val exoPlayer : ExoPlayer) : VideoPlayerRepository {
    override fun release(){
        exoPlayer.release()
    }

    override fun play(uri : Uri?){
        if(uri != null) {
            exoPlayer.setMediaItem(MediaItem.fromUri(uri))
            exoPlayer.prepare()
            exoPlayer.play()
        }
    }

}