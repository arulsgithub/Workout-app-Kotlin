package com.example.meditationui.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meditationui.ui.theme.DeepBlue
import com.example.meditationui.ui.theme.TextWhite
import com.example.meditationui.userData


@Composable
fun ArmWorkout(userData: userData?, navController: NavController) {

    val workoutToUrlMap = mapOf(
        "Tricep Dips" to "https://assets.mixkit.co/videos/24309/24309-720.mp4",
        "Tricep Kickback" to "https://media.istockphoto.com/id/1492806635/video/3d-rendered-animation-showcasing-the-tricep-exercise-with-dumbbells-on-the-white-background.mp4?s=mp4-480x480-is&k=20&c=8E8L5OFE-tEcAh0pnjPZM0kICgd1TN3GcwApgs8RGC4=",
        "Overhead Tricep Extension" to "https://assets.mixkit.co/videos/24309/24309-720.mp4",
        "Skull Crushers" to "https://assets.mixkit.co/videos/24309/24309-720.mp4",
        "Close-Grip Bench Press" to "https://assets.mixkit.co/videos/24309/24309-720.mp4"
    )
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
            .padding(top = 35.dp, bottom = 100.dp)
    ) {

        Greeting(name = "Arul", subTitle = "Here your arm workouts!🔥",userData,navController)

        LazyColumn(
            modifier = Modifier
                .padding(top = 70.dp)
        ) {

            item {

                Text(
                    text = "Bicep Workouts",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextWhite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }

            item {
                WorkoutChips(
                    workOut = listOf(
                        "Barbell Curl",
                        "Dumbbell Curl",
                        "Hammer Curl",
                        "Dumbbell Reverse Curl",
                        "Concentration Curl"
                    ),navController
                )
            }

            item {
                Text(
                    text = "Tricep Workouts",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextWhite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }

            item {
                WorkoutChips(
                    workOut = listOf(
                        "Tricep Dips",
                        "Tricep Kickback",
                        "Overhead Tricep Extension",
                        "Skull Crushers",
                        "Close-Grip Bench Press"
                    ),navController
                )
            }
        }
    }
}

@Composable
fun AbsWorkout(userData: userData?, navController: NavController) {

    val workoutToUrlMap = mapOf(
        "Leg Raise" to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5001.mp4",
        "Crunchs" to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5002.mp4",
        "Bicycle Crunch" to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5006.mp4",
        "V Up " to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5010.mp4",
        "Full Plank" to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5034.mp4",
        "Mountain Climber" to "https://planfit-images.s3.ap-northeast-2.amazonaws.com/training-videos-watermarked/5025.mp4"
    )
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
            .padding(top = 35.dp)
    ) {

        Greeting(name = "Arul", subTitle = "Here your arm workouts!🔥",userData,navController)

        LazyColumn(
            modifier = Modifier
                .padding(top = 70.dp)
        ) {

            item {

                Text(
                    text = "ABS Workouts",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextWhite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }

            item {
                WorkoutChips(
                    workOut = listOf(
                        "Leg Raise",
                        "Crunch",
                        "Bicycle Crunch",
                        "V Up",
                        "Full Plank",
                        "Mountain Climber"
                    ),navController
                )
            }

        }
    }
}

@Composable
fun ShoulderWorkout(userData: userData?, navController: NavController) {

    val workoutToUrlMap = mapOf(
        "Dumbbell Bench Seated Press" to "https://media.istockphoto.com/id/1496326941/video/animation-of-a-fit-man-exercising-dumbbell-bench-seated-press-fitness-workout.mp4?s=mp4-640x640-is&k=20&c=hFnbrHQDw1xmqwoPqsaIACsrMwn1YBFfauqWr3uby2M=",
        "Lateral Raises" to "https://media.istockphoto.com/id/1495656595/video/3d-rendered-animation-of-a-sportsman-training-with-dumbbells-isolated-on-a-white-background.mp4?s=mp4-640x640-is&k=20&c=98iEahwPG7t3ROjSwRLud5fcs5vTb0DXJcnx5xlvfQs=",
        "Front Raises" to "https://media.istockphoto.com/id/1496158641/video/3d-rendered-animation-of-a-sportsman-training-serratus-anterior-and-trapezius-muscles-with.mp4?s=mp4-640x640-is&k=20&c=pFFz-UPUyt1s3pxvjn6wq8nBzfRkECf15pRwpmAR4hw=",
        "External Rotation" to "https://media.istockphoto.com/id/1498230659/video/3d-rendered-animation-of-a-shoulder-exercise-example-on-a-white-background.mp4?s=mp4-640x640-is&k=20&c=95fIMcwz8gmQ7uYn8Sy-OPiIcCeNIpDWBFzW_xQPIGM=",
        "Hammer Front Raises (alternate)" to "https://media.istockphoto.com/id/1496193738/video/3d-rendered-animation-of-a-model-doing-exercise-with-dumbbells-on-the-empty-white-background.mp4?s=mp4-640x640-is&k=20&c=nT8tZ1A9oy-22xAi2YryQaGLJLG-IKHXQgdUng_lb2c=",
        "Shrugs" to "https://media.istockphoto.com/id/1495681191/video/3d-rendered-animation-of-shoulders-exercise-example-on-a-white-background.mp4?s=mp4-640x640-is&k=20&c=uWvrmwbcskg4PD0MxgvgdO0nSpOabS4eFh0z9R8M8a0="
    )
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
            .padding(top = 35.dp)
    ) {

        Greeting(name = "Arul", subTitle = "Here your arm workouts!🔥",userData,navController)

        LazyColumn(
            modifier = Modifier
                .padding(top = 70.dp)
        ) {

            item {

                Text(
                    text = "Shoulder Workouts",
                    style = MaterialTheme.typography.headlineMedium,
                    color = TextWhite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }

            item {
                WorkoutChips(
                    workOut = listOf(
                        "Dumbbell Bench Seated Press",
                        "Lateral Raises",
                        "Front Raises",
                        "External Rotation",
                        "Hammer Front Raises (alternate)",
                        "Shrugs"
                    ),navController
                )
            }

        }
    }
}


@Composable
fun WorkoutChips(
    workOut: List<String>,
    navController: NavController
) {
    var selectedChipIndex by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        workOut.forEachIndexed { index, workout ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .clickable {
                        //val url = videoUrl[workout] ?: ""
                        //navController.navigate("videoPlayer/${Uri.encode(workout)}/${Uri.encode(url)}")
                        navController.navigate(workout)
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF1C3170),
                                Color(0xFF864042)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                Text(text = workout, color = TextWhite)
            }
        }
    }
}





/*
@Preview(showBackground = true)
@Composable
fun WithGreenBackground() {
    HomeScreen()
}*/
