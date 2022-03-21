package com.example.spotifyclone.exoplayer

import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.MediaMetadataCompat.*
import androidx.core.net.toUri
import com.example.spotifyclone.data.entities.remote.MusicDatabase
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.withContext
import javax.inject.Inject

@InternalCoroutinesApi
class FireBaseMusicSource @Inject constructor(
    private val musicDatabase: MusicDatabase
){

    var songs = emptyList<MediaMetadataCompat>()
    suspend fun fetchMediaData()= withContext(Dispatchers.IO){
        state = State.STATE_INITIALIZING
        val allSongs = musicDatabase.getAllSongs()
        songs =allSongs.map { song ->
            MediaMetadataCompat.Builder()
                .putString(METADATA_KEY_ARTIST,song.subtitle)
                .putString(METADATA_KEY_MEDIA_ID,song.mediaId)
                .putString(METADATA_KEY_TITLE,song.title)
                .putString(METADATA_KEY_DISPLAY_TITLE,song.title)
                .putString(METADATA_KEY_DISPLAY_ICON_URI,song.imageURL)
                .putString(METADATA_KEY_ALBUM_ART_URI,song.imageURL)
                .putString(METADATA_KEY_DISPLAY_SUBTITLE,song.subtitle)
                .build()
        }
    }
    fun asMediaSource(dataSourceFactory: DefaultMediaSourceFactory): ConcatenatingMediaSource{
        val concatenatingMediaSource = ConcatenatingMediaSource()
        songs.forEach { song ->
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(song.getString(METADATA_KEY_MEDIA_URI).toUri())
                concatenatingMediaSource.addMediaSource(mediaSource)

        }
        return concatenatingMediaSource

    }

    private val onReadyListeners = mutableListOf<(Boolean)->Unit>()

    private var state: State = State.STATE_CREATED
        set(value){
            if (value == State.STATE_INITILAIZED|| value== State.STATE_ERROR) {
                synchronized(onReadyListeners) {
                    field = value
                    onReadyListeners.forEach { listener ->
                        listener(state == State.STATE_INITIALIZING)
                    }
                }
            }
                else{
                    field =value
                }
            }
    fun whenReady(action:(Boolean)->Unit): Boolean {
        if (state == State.STATE_CREATED || state== State.STATE_INITIALIZING)
        {
        onReadyListeners += action
            return false
        } else{
        action(state == State.STATE_INITIALIZING)
            return true
        }
    }
        }



enum class State {
    STATE_CREATED,
    STATE_INITIALIZING,
    STATE_INITILAIZED,
    STATE_ERROR
}