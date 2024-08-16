package com.example.meditationui.ui.theme.screens.workoutsScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.meditationui.ui.theme.DeepBlue
import com.example.meditationui.ui.theme.TextWhite


@Composable
fun LegRaise(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5001.mp4"
    val player = ExoPlayer.Builder(context).build().apply {
        setMediaItem(androidx.media3.common.MediaItem.fromUri(videoUrl))
        repeatMode = ExoPlayer.REPEAT_MODE_ALL
    }
    val playerView = PlayerView(context)
    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }
    playerView.player = player

    LaunchedEffect(player) {

        player.prepare()
        player.playWhenReady = playWhenReady

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(top=30.dp,start = 16.dp,end = 16.dp)
    ) {
        Text(
            text = "Dumbbell Curl",
            style = MaterialTheme.typography.headlineMedium,
            color = TextWhite,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            factory = {
                playerView
            }
        )
    }
}


@Composable
fun MountainClimber(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5025.mp4"
    val player = ExoPlayer.Builder(context).build().apply {
        setMediaItem(androidx.media3.common.MediaItem.fromUri(videoUrl))
        repeatMode = ExoPlayer.REPEAT_MODE_ALL
    }
    val playerView = PlayerView(context)
    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }
    playerView.player = player

    LaunchedEffect(player) {

        player.prepare()
        player.playWhenReady = playWhenReady

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(top=30.dp,start = 16.dp,end = 16.dp)
    ) {
        Text(
            text = "Dumbbell Curl",
            style = MaterialTheme.typography.headlineMedium,
            color = TextWhite,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            factory = {
                playerView
            }
        )
    }
}
@Composable
fun Crunch(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5002.mp4"
    val player = ExoPlayer.Builder(context).build().apply {
        setMediaItem(androidx.media3.common.MediaItem.fromUri(videoUrl))
        repeatMode = ExoPlayer.REPEAT_MODE_ALL
    }
    val playerView = PlayerView(context)
    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }
    playerView.player = player

    LaunchedEffect(player) {

        player.prepare()
        player.playWhenReady = playWhenReady

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(top=30.dp,start = 16.dp,end = 16.dp)
    ) {
        Text(
            text = "Crunch",
            style = MaterialTheme.typography.headlineMedium,
            color = TextWhite,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            factory = {
                playerView
            }
        )
    }
}

@Composable
fun BicycleCrunch(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5006.mp4"
    val player = ExoPlayer.Builder(context).build().apply {
        setMediaItem(androidx.media3.common.MediaItem.fromUri(videoUrl))
        repeatMode = ExoPlayer.REPEAT_MODE_ALL
    }
    val playerView = PlayerView(context)
    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }
    playerView.player = player

    LaunchedEffect(player) {

        player.prepare()
        player.playWhenReady = playWhenReady

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(top=30.dp,start = 16.dp,end = 16.dp)
    ) {
        Text(
            text = "Bicycle Crunch",
            style = MaterialTheme.typography.headlineMedium,
            color = TextWhite,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            factory = {
                playerView
            }
        )
    }
}
@Composable
fun VUp(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5010.mp4"
    val player = ExoPlayer.Builder(context).build().apply {
        setMediaItem(androidx.media3.common.MediaItem.fromUri(videoUrl))
        repeatMode = ExoPlayer.REPEAT_MODE_ALL
    }
    val playerView = PlayerView(context)
    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }
    playerView.player = player

    LaunchedEffect(player) {

        player.prepare()
        player.playWhenReady = playWhenReady

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(top=30.dp,start = 16.dp,end = 16.dp)
    ) {
        Text(
            text = "V Up",
            style = MaterialTheme.typography.headlineMedium,
            color = TextWhite,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            factory = {
                playerView
            }
        )
    }
}
@Composable
fun FullPlank(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5034.mp4"
    val player = ExoPlayer.Builder(context).build().apply {
        setMediaItem(androidx.media3.common.MediaItem.fromUri(videoUrl))
        repeatMode = ExoPlayer.REPEAT_MODE_ALL
    }
    val playerView = PlayerView(context)
    val playWhenReady by rememberSaveable {
        mutableStateOf(true)
    }
    playerView.player = player

    LaunchedEffect(player) {

        player.prepare()
        player.playWhenReady = playWhenReady

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(top=30.dp,start = 16.dp,end = 16.dp)
    ) {
        Text(
            text = "Full Plank",
            style = MaterialTheme.typography.headlineMedium,
            color = TextWhite,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            factory = {
                playerView
            }
        )
    }
}

