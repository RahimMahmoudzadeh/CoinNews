package com.rahim.coinnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.rahim.coinnews.library.designsystem.theme.CoinNewsTheme
import com.rahim.coinnews.library.navigation.config.ConfigChildComponent
import com.rahim.coinnews.navigation.BottomNavigationBar
import com.rahim.coinnews.navigation.RootComponentImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val root = RootComponentImpl(componentContext = defaultComponentContext())

        setContent {
            val stack = root.stack.subscribeAsState()
            val configurationState = stack.value.active.configuration

            CoinNewsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (configurationState is ConfigChildComponent.Home) {
                            BottomNavigationBar(
                                configuration = configurationState,
                                component = root
                            )
                        }
                    }
                ) { innerPadding ->
                    RootContent(component = root, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}