package com.cypress.cyvideoplayer.repositories

expect class VideoItem {
    val id: Long
    val title: String
    val uri: String
}
expect interface VideoListRepository
expect class VideoListRepositoryImp