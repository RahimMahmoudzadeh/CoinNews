package com.rahim.coinnews.library.designsystem.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rahim.coinnews.library.designsystem.preview.ThemePreviews
import com.rahim.coinnews.library.designsystem.theme.CoinNewsTheme

@Composable
fun ErrorView(errorMessage: String) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 20.dp),
        )
    }
}

@ThemePreviews
@Composable
private fun ErrorViewPrev() {
    CoinNewsTheme {
        ErrorView(errorMessage = "An unknown error happened!")
    }
}
