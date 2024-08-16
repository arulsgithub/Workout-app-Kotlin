package com.example.meditationui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.meditationui.classes.BottomMenuContents
import com.example.meditationui.classes.GoogleAuth
import com.example.meditationui.classes.SignInViewModel
import com.example.meditationui.ui.theme.*
import com.example.meditationui.ui.theme.screens.AbsWorkout
import com.example.meditationui.ui.theme.screens.AnalyseScreen
import com.example.meditationui.ui.theme.screens.ArmWorkout
import com.example.meditationui.ui.theme.screens.CommunityScreen
import com.example.meditationui.ui.theme.screens.HomeScreen
import com.example.meditationui.ui.theme.screens.ProfileScreen
import com.example.meditationui.ui.theme.screens.SettingsScreen
import com.example.meditationui.ui.theme.screens.ShoulderWorkout
import com.example.meditationui.ui.theme.screens.SignInScreen
import com.example.meditationui.ui.theme.screens.workoutsScreens.BarbellCurl
import com.example.meditationui.ui.theme.screens.workoutsScreens.BicycleCrunch
import com.example.meditationui.ui.theme.screens.workoutsScreens.ConcentrationCurl
import com.example.meditationui.ui.theme.screens.workoutsScreens.Crunch
import com.example.meditationui.ui.theme.screens.workoutsScreens.DumbbellCurl
import com.example.meditationui.ui.theme.screens.workoutsScreens.DumbbellReverseCurl
import com.example.meditationui.ui.theme.screens.workoutsScreens.FullPlank
import com.example.meditationui.ui.theme.screens.workoutsScreens.HammerCurl
import com.example.meditationui.ui.theme.screens.workoutsScreens.LegRaise
import com.example.meditationui.ui.theme.screens.workoutsScreens.MountainClimber
import com.example.meditationui.ui.theme.screens.workoutsScreens.VUp
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuth(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeditationUITheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DeepBlue),
                ) {
                    val navController = rememberNavController()
                    val noBottomBarRoutes = listOf("signIn")

                    Scaffold(
                        bottomBar = {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route
                            if (currentRoute !in noBottomBarRoutes) {
                                BottomNavigationMenu(
                                    items = listOf(
                                        BottomMenuContents("Home", R.drawable.ic_home),
                                        BottomMenuContents("Analyse", R.drawable.ic_analyse),
                                        BottomMenuContents("Community", R.drawable.ic_community),
                                        BottomMenuContents("Settings", R.drawable.ic_settings)
                                    ),
                                    navController = navController
                                )
                            }
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "signIn"
                        ) {
                            composable("signIn") {
                                val viewModel = viewModel<SignInViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()

                                val launcher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                                    onResult = { result ->
                                        if (result.resultCode == RESULT_OK) {
                                            lifecycleScope.launch {
                                                val signInResult = googleAuthUiClient.signInwithIntent(
                                                    intent = result.data ?: return@launch
                                                )
                                                viewModel.onSignInResult(signInResult)
                                            }
                                        }
                                    }
                                )

                                LaunchedEffect(key1 = state.isSuccessful) {
                                    if (state.isSuccessful) {
                                        Toast.makeText(
                                            applicationContext,
                                            "Sign in successful",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navController.navigateAndClearStack("home")
                                    }
                                }
                                SignInScreen(
                                    state = state,
                                    onSignInClick = {
                                        lifecycleScope.launch {
                                            val signInIntentSender = googleAuthUiClient.signIn()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender ?: return@launch
                                                ).build()
                                            )
                                        }
                                    }
                                )
                            }
                            composable("home") {
                                val viewModel = viewModel<SignInViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                HomeScreen(userData = googleAuthUiClient.getSigneduser(), navController)
                            }
                            composable("profile") {
                                val viewModel = viewModel<SignInViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()

                                val launcher = rememberLauncherForActivityResult(
                                    contract = ActivityResultContracts.StartIntentSenderForResult(),
                                    onResult = { result ->
                                        if (result.resultCode == RESULT_OK) {
                                            lifecycleScope.launch {
                                                val signInResult = googleAuthUiClient.signInwithIntent(
                                                    intent = result.data ?: return@launch
                                                )
                                                viewModel.onSignInResult(signInResult)
                                            }
                                        }
                                    }
                                )
                                ProfileScreen(
                                    userData = googleAuthUiClient.getSigneduser(),
                                    onSignOut = {
                                        lifecycleScope.launch {
                                            googleAuthUiClient.signOut()
                                            Toast.makeText(
                                                applicationContext,
                                                "Signed out",
                                                Toast.LENGTH_LONG
                                            ).show()
                                            navController.navigateAndClearStack("home")
                                        }
                                    },
                                    navController = navController,
                                    onSignIn = {
                                        lifecycleScope.launch {
                                            val signInIntentSender = googleAuthUiClient.signIn()
                                            launcher.launch(
                                                IntentSenderRequest.Builder(
                                                    signInIntentSender ?: return@launch
                                                ).build()
                                            )
                                        }
                                    }
                                )
                            }
                            composable("analyse") { AnalyseScreen() }
                            composable("community") { CommunityScreen() }
                            composable("settings") { SettingsScreen() }
                            composable("armWorkout") {
                                val viewModel = viewModel<SignInViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()

                                ArmWorkout(userData = googleAuthUiClient.getSigneduser(), navController)
                            }
                            composable("shoulderWorkout") {
                                val viewModel = viewModel<SignInViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                ShoulderWorkout(userData = googleAuthUiClient.getSigneduser(), navController)
                            }
                            composable("absWorkout") {
                                val viewModel = viewModel<SignInViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()
                                AbsWorkout(userData = googleAuthUiClient.getSigneduser(), navController)
                            }
                            composable("Dumbbell Curl") { DumbbellCurl(navController) }
                            composable("Barbell Curl") { BarbellCurl(navController) }
                            composable("Hammer Curl") { HammerCurl(navController) }
                            composable("Dumbbell Reverse Curl") { DumbbellReverseCurl(navController) }
                            composable("Concentration Curl") { ConcentrationCurl(navController) }
                            composable("Mountain Climber") { MountainClimber(navController) }
                            composable("Full Plank") { FullPlank(navController) }
                            composable("V Up") { VUp(navController) }
                            composable("Bicycle Crunch") { BicycleCrunch(navController) }
                            composable("Crunch") { Crunch(navController) }
                            composable("Leg Raise") { LegRaise(navController) }
                        }
                    }
                }
            }
        }
    }
}

fun NavController.navigateAndClearStack(route: String) {
    this.navigate(route) {
        popUpTo(this@navigateAndClearStack.graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun BottomNavigationMenu(
    items: List<BottomMenuContents>,
    navController: NavController,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = DeepBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var selectedItemIndex by remember {
        mutableStateOf(
            items.indexOfFirst { it.title.equals(currentRoute, ignoreCase = true) }
                .takeIf { it != -1 } ?: initialSelectedItemIndex
        )
    }

    // Update the selected index whenever the current route changes
    LaunchedEffect(currentRoute) {
        selectedItemIndex = items.indexOfFirst { it.title.equals(currentRoute, ignoreCase = true) }
            .takeIf { it != -1 } ?: initialSelectedItemIndex
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .size(height = 80.dp, width = 300.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(DeepBlue)
                .padding(start = 25.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
        ) {
            items.forEachIndexed { index, item ->
                BottomMenuItems(
                    item = item,
                    isSelected = index == selectedItemIndex,
                    activeTextColor = activeTextColor,
                    inactiveTextColor = inactiveTextColor
                ) {
                    selectedItemIndex = index
                    if (item.title == "Home") {
                        navController.navigateAndClearStack(item.title)
                    } else {
                        navController.navigate(item.title) {
                            // Avoid building up a large stack of destinations
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomMenuItems(
    item: BottomMenuContents,
    isSelected: Boolean = false,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding()
            .clickable {
                onItemClick()
            }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .padding(5.dp)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(
                    if (isSelected) 30.dp else 20.dp
                )
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inactiveTextColor,
            fontSize = 12.sp
        )
    }
}
