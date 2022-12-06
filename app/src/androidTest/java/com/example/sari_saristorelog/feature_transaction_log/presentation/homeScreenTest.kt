package com.example.sari_saristorelog.feature_transaction_log.presentation

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.example.sari_saristorelog.MainActivity
import com.example.sari_saristorelog.di.AppModule
import com.example.sari_saristorelog.feature_transaction_log.presentation.homeScreen.HomeScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class homeScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup(){
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "Home"){
                composable("Home"){
                    HomeScreen()
                }
            }
        }
    }

    @Test
    fun test1() = runTest{
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithContentDescription("customerIcon").assertExists()
    }
}