package com.example.sari_saristorelog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sari_saristorelog.core.util.BottomNavigationItems
import com.example.sari_saristorelog.feature_transaction_log.presentation.AddEditTransaction.AddEditTransactionScreen
import com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.HomeScreen
import com.example.sari_saristorelog.ui.theme.SariSariStoreLogTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SariSariStoreLogTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    val scaffoldState = rememberScaffoldState()


                    Scaffold(
                        bottomBar = { BottomNavigation(backgroundColor = Color.White
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            BottomNavigationItems.NavList.navItems.forEach { navItem ->
                                BottomNavigationItem(
                                    icon = {
                                           Icon(painter = painterResource(id = navItem.icon), contentDescription = "navIcon")
                                    },
                                    label = { Text(
                                        text = stringResource(id = navItem.label),
                                        style = MaterialTheme.typography.h1,
                                        fontSize = 15.sp)},
                                    selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                                    onClick = {
                                        navController.navigate(navItem.route){
                                            popUpTo(navController.graph.findStartDestination().id)

                                            launchSingleTop = true
                                        }
                                    })

                            }
                        }}
                    ) { innerPadding ->


                        NavHost(
                            navController = navController,
                            startDestination = BottomNavigationItems.Home.route,
                            modifier = Modifier.padding(innerPadding)){
                            composable(BottomNavigationItems.Home.route){
                                HomeScreen()
                            }
                            composable(BottomNavigationItems.Add.route){
                                AddEditTransactionScreen(navController = navController)
                            }
                        }
                        
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SariSariStoreLogTheme {
        Greeting("Android")
    }
}