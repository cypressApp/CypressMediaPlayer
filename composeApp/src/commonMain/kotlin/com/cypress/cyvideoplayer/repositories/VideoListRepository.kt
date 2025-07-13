package com.cypress.cyvideoplayer.repositories

data class VideoItem(val id: Long, val title: String?, val uriString: String)
expect interface VideoListRepository
expect class VideoListRepositoryImp