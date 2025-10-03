package com.rahim.coinnews.coindetail.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import com.rahim.coinnews.coindetail.presentation.model.MarketChartPresentationLayerCoinDetail
import com.rahim.coinnews.core.utils.LoadableComponent
import com.rahim.coinnews.core.utils.LoadableData
import com.rahim.coinnews.library.designsystem.R
import com.rahim.coinnews.library.designsystem.component.QuadLineChart
import com.rahim.coinnews.library.designsystem.component.shimmerEffect

@Composable
internal fun QuadLineChart(
    loadableData: LoadableData<MarketChartPresentationLayerCoinDetail>,
) {
    LoadableComponent(
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.quad_line_chart_height))
                    .padding(dimensionResource(R.dimen.quad_line_chart_padding))
                    .clip(MaterialTheme.shapes.large)
                    .shimmerEffect(),
            )
        },
        loaded = { data ->
            QuadLineChart(
                data = data.prices,
            )
        },
        error = { error -> },
        loadableData = loadableData,
    )
}
