package com.rahim.coinnews.home.presentation.previewProvider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.rahim.coinnews.home.presentation.navigation.HomeComponent

class HomeStateProvider : PreviewParameterProvider<HomeComponent.State> {
    override val values: Sequence<HomeComponent.State> = sequenceOf(
        HomeComponent.State(),
    )
}
