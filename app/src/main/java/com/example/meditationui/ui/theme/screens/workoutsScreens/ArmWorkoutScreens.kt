package com.example.meditationui.ui.theme.screens.workoutsScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import com.example.meditationui.R
import com.example.meditationui.classes.ToggleInfo
import com.example.meditationui.ui.theme.ButtonBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DumbbellCurl(navController: NavController) {

    val context = LocalContext.current
    val videoUrl = "https://d2vlg658oxixzt.cloudfront.net/male_dumbbell_curl_front_ani.mp4"
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl))
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
            prepare()
        }
    }
    val coroutineScope = rememberCoroutineScope()
    var isError by rememberSaveable { mutableStateOf(false) }
    var playWhenReady by rememberSaveable { mutableStateOf(true) }

    DisposableEffect(player) {
        onDispose {
            player.release()
        }
    }

    LaunchedEffect(player) {
        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                coroutineScope.launch {
                    isError = true
                }
            }

            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_BUFFERING -> {
                        println("Video is buffering...")
                    }
                    Player.STATE_READY -> {
                        println("Video is ready to play.")
                        isError = false
                    }
                    Player.STATE_ENDED -> {
                        println("Video playback ended.")
                    }
                    Player.STATE_IDLE -> {
                        println("Player is idle.")
                    }
                }
            }
        })

        player.playWhenReady = playWhenReady
    }

    fun reloadVideo() {
        player.setMediaItem(MediaItem.fromUri(videoUrl))
        player.prepare()
        player.playWhenReady = playWhenReady
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp)
    ) {
        Text(
            text = "Dumbbell Curl",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
            modifier = Modifier.padding(vertical =30.dp)
        )

        if (!isError) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black)
                    .clickable {
                        playWhenReady = !playWhenReady
                        player.playWhenReady = playWhenReady
                    }
            ) {
                AndroidView(
                    factory = { ctx ->
                        PlayerView(ctx).apply {
                            this.player = player
                        }
                    },
                    modifier = Modifier.matchParentSize()
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Video cannot be loaded. Please check your internet connection.",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = { reloadVideo() },
                    colors = ButtonDefaults.buttonColors(ButtonBlue),
                    modifier = Modifier
                        .padding(top = 100.dp)
                ) {
                    Text(text = "Reload", color = Color.White)
                }
            }
        }

        Text(
            text = "Preperations",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 20.dp, bottom = 15.dp)
        )


        // Preparation steps
        val steps = listOf(
            "Stand upright holding a dumbbell in each hand at arm's length.",
            "Keep your elbows close to your torso and rotate the palms of your hands until they are facing forward.",
            "Keeping the upper arms stationary, exhale and curl the weights while contracting your biceps.",
            "Continue to raise the weights until your biceps are fully contracted and the dumbbells are at shoulder level.",
            "Hold the contracted position for a brief pause as you squeeze your biceps.",
            "Inhale and slowly begin to lower the dumbbells back to the starting position.",

        )

        steps.forEachIndexed { index, step ->
            Text(
                text = "${index + 1}. $step",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 18.dp, horizontal = 10.dp)
            )
        }
        CheckBoxes("10",4)
    }
}
@Composable
fun CheckBoxes(
    count: String,
    initialSetCount: Int
) {
    var setCount by remember { mutableStateOf(initialSetCount) }
    var completed by remember { mutableStateOf(0) }

    val checkBoxes = remember {
        mutableStateListOf<ToggleInfo>().apply {
            repeat(initialSetCount) {
                add(
                    ToggleInfo(
                        set = "Set ${it + 1}",
                        reps = "$count Reps",
                        isChecked = false
                    )
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF06164C)) // Use the screen color
    ) {
        checkBoxes.forEachIndexed { index, info ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF1C3170),
                                Color(0xFF864042)
                            )
                        )
                    )
            ) {
                Text(
                    text = info.set,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = info.reps,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Checkbox(
                    checked = info.isChecked,
                    onCheckedChange = { isChecked ->
                        checkBoxes[index] = info.copy(isChecked = isChecked)
                        completed = checkBoxes.count { it.isChecked }
                    }
                )
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 16.dp, end = 16.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(60.dp))
                .background(Color.Gray)
                .clickable {
                    setCount += 1
                    checkBoxes.add(
                        ToggleInfo(
                            set = "Set ${checkBoxes.size + 1}",
                            reps = "$count Reps",
                            isChecked = false
                        )
                    )
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "Add", // Use a relevant description
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 16.dp, end = 16.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(60.dp))
                .background(
                    if (completed == setCount) Color(0xFF4CEB6E) else Color(0xFF6A706B)
                )
                .clickable {
                }
        ) {
            Text(
                text = "Finished",
                color = if (completed == setCount) Color.White else Color(0xFFA4F1F9),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun BarbellCurl(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://assets.mixkit.co/videos/24309/24309-720.mp4"
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
            .padding(top = 30.dp, start = 16.dp, end = 16.dp)
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
fun HammerCurl(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://d2vlg658oxixzt.cloudfront.net/male_dumbbell_hammer_curl_front_ani.mp4"
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
            .padding(top = 30.dp, start = 16.dp, end = 16.dp)
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
fun DumbbellReverseCurl(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://d2vlg658oxixzt.cloudfront.net/male_dumbbell_reverse_curl_front_ani.mp4"
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
            .padding(top = 30.dp, start = 16.dp, end = 16.dp)
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
fun ConcentrationCurl(navController: NavController){
    val context = LocalContext.current
    val videoUrl = "https://d2vlg658oxixzt.cloudfront.net/male-dumbbell-concentration-curl-front-ani.mp4"
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
            .padding(top = 30.dp, start = 16.dp, end = 16.dp)
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
