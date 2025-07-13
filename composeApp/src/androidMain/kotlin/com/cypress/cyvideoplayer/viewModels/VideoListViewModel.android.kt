package com.cypress.cyvideoplayer.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cypress.cyvideoplayer.repositories.VideoItem
import com.cypress.cyvideoplayer.repositories.VideoListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

actual class VideoListViewModel(
    val videoListRepository: VideoListRepository
) : ViewModel(){

    private val _videoList = MutableStateFlow<List<VideoItem>>(emptyList())
    val videoList : StateFlow<List<VideoItem>> get() = _videoList

//    init {
//        getAllList()
//    }

    fun getAllList(){
        viewModelScope.launch{
            val tempVideoList = videoListRepository.getVideoList()
            _videoList.value = tempVideoList
        }
    }

}