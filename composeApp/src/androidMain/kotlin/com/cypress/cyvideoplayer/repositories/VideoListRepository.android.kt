package com.cypress.cyvideoplayer.repositories

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import android.util.Log

actual interface VideoListRepository{

    fun getVideoList(): List<VideoItem>

}

actual class VideoListRepositoryImp(val context: Context) : VideoListRepository{

    override fun getVideoList(): List<VideoItem> {

        val videoList = mutableListOf<VideoItem>()
        val collection = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.TITLE
        )

        context.contentResolver.query(
            collection,
            projection,
            null,
            null,
            "${MediaStore.Video.Media.DATE_ADDED} DESC"
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(titleColumn)
                val uri = ContentUris.withAppendedId(collection, id)
                videoList.add(VideoItem(id, title, uri.toString()))
            }
        }
        Log.e("VideoList" , "LIST Size : ${videoList.size}")
        return videoList
    }


}
