plugins {
    libs.plugins.coinNews.run {
        alias(feature)
        alias(library.compose)
    }
}

android {
    namespace = "com.rahim.coinnews.coindetail.presentation"
}

dependencies{
    projects.run {
        implementation(coindetail.domain)
        implementation(core.network)
    }
    libs.bundles.run{
        implementation(coil)
    }
}