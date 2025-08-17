package com.rahim.coinnews.library.navigation.config

import kotlinx.serialization.Serializable

@Serializable
sealed class ConfigChildComponent {
    data object Home : ConfigChildComponent()
    data object Favorites : ConfigChildComponent()
}