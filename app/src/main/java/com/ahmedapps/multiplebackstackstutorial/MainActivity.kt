package com.ahmedapps.multiplebackstackstutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ahmedapps.multiplebackstackstutorial.screens.LastScreen
import com.ahmedapps.multiplebackstackstutorial.screens.Screen
import com.ahmedapps.multiplebackstackstutorial.ui.theme.MultipleBackstacksTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultipleBackstacksTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    val bottomBarNavController = rememberNavController()
                    val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()

                    Scaffold(
                        bottomBar = {
                            NavigationBar {
                                items.forEach { item ->
                                    val isSelected =
                                        item.route == navBackStackEntry?.destination?.route

                                    NavigationBarItem(
                                        selected = isSelected,
                                        onClick = {
                                            bottomBarNavController.navigate(item.route) {
                                                popUpTo(bottomBarNavController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                restoreState = true
                                                launchSingleTop = true
                                            }
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (isSelected) item.selectedIcon
                                                else item.unselectedIcon,
                                                contentDescription = item.title
                                            )
                                        },
                                        label = {
                                            Text(text = item.title)
                                        }
                                    )
                                }
                            }
                        }
                    ) { paddingValues ->
                        BottomNavigation(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    bottom = paddingValues.calculateBottomPadding()
                                ),
                            bottomBarNavController = bottomBarNavController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigation(
    modifier: Modifier,
    bottomBarNavController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = bottomBarNavController,
        startDestination = "home"
    ) {

        composable(route = "home") {
            ScreensNavigation(selectedBottomBarItem = "Home")
        }

        composable(route = "settings") {
            ScreensNavigation(selectedBottomBarItem = "Settings")
        }

        composable(route = "profile") {
            ScreensNavigation(selectedBottomBarItem = "Profile")
        }

    }
}

@Composable
fun ScreensNavigation(selectedBottomBarItem: String) {
    val screensNavController = rememberNavController()
    NavHost(
        navController = screensNavController,
        startDestination = "$selectedBottomBarItem 1"
    ) {
        composable(route = "$selectedBottomBarItem 1") {
            Screen(
                screensNavController = screensNavController,
                screenName = selectedBottomBarItem,
                screenNumber = 1
            )
        }

        composable(route = "$selectedBottomBarItem 2") {
            Screen(
                screensNavController = screensNavController,
                screenName = selectedBottomBarItem,
                screenNumber = 2
            )
        }

        composable(route = "$selectedBottomBarItem 3") {
            Screen(
                screensNavController = screensNavController,
                screenName = selectedBottomBarItem,
                screenNumber = 3
            )
        }

        composable(route = "$selectedBottomBarItem 4") {
            Screen(
                screensNavController = screensNavController,
                screenName = selectedBottomBarItem,
                screenNumber = 4
            )
        }

        composable(route = "$selectedBottomBarItem 4") {
            LastScreen(
                screenNumber = 4
            )
        }
    }
}

data class BottomNavigationItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

val items = listOf(
    BottomNavigationItem(
        route = "home",
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    BottomNavigationItem(
        route = "settings",
        title = "Settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
    ),
    BottomNavigationItem(
        route = "profile",
        title = "Profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle
    )
)









