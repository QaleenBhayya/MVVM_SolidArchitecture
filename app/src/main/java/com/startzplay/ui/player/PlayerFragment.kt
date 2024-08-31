package com.startzplay.ui.player

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.startzplay.R
import com.startzplay.base.BaseFragment
import com.startzplay.databinding.FragmentPlayerBinding
import com.startzplay.utils.showToast
import com.startzplay.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlayerFragment : BaseFragment(R.layout.fragment_player) {

    private val mBinding by viewBinding(FragmentPlayerBinding::bind)
    private lateinit var player: ExoPlayer

    private val videoUrl =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        initPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    override fun onStop() {
        super.onStop()
        player.release()
    }

    //////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////

    @OptIn(UnstableApi::class)
    private fun initPlayer() {
        player = ExoPlayer.Builder(requireContext()).build()

        val mediaItem = MediaItem.fromUri("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        val mediaSource = ProgressiveMediaSource.Factory(DefaultDataSource.Factory(requireContext()))
            .createMediaSource(mediaItem)

        player.setMediaSource(mediaSource)
        player.prepare()
        player.playWhenReady = true

        mBinding.playerEpVideo.player = player

        player.addListener(object :Player.Listener{
            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                requireActivity().showToast(error.message?:"Something went wrong")
            }
        })
    }
}