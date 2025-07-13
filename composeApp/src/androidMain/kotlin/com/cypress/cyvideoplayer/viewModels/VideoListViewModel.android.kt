package com.cypress.cyvideoplayer.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cypress.cyvideoplayer.common.Resources
import com.cypress.cyvideoplayer.repositories.VideoItem
import com.cypress.cyvideoplayer.repositories.VideoListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

actual class VideoListViewModel(
    val videoListRepository: VideoListRepository
) : ViewModel(){

    private val _videoList = MutableStateFlow<List<VideoItem>>(emptyList())
    val videoList : StateFlow<List<VideoItem>> get() = _videoList

    fun getAllList(){
        viewModelScope.launch{
            val tempList = mutableListOf<VideoItem>()
            withContext(Dispatchers.IO){
                videoListRepository.getVideoList().collect { videoItem ->
                    when(videoItem){
                        is Resources.Success -> videoItem.data?.let { tempList.add(it) }
                        is Resources.Error -> TODO()
                        is Resources.Loading -> TODO()
                    }

                    _videoList.value = tempList
                }
            }
        }
    }

}