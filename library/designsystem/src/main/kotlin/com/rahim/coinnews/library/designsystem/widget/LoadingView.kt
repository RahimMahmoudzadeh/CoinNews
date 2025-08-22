package com.rahim.coinnews.library.designsystem.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rahim.coinnews.library.designsystem.preview.ThemePreviews
import com.rahim.coinnews.library.designsystem.theme.CoinNewsTheme


@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(34.dp)
                .width(34.dp)
                .align(Alignment.Center),
        )
    }
}

@ThemePreviews
@Composable
private fun LoadingViewPreview() {
    CoinNewsTheme {
        Surface {
            LoadingView()
        }
    }
}
