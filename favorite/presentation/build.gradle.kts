plugins {
    libs.plugins.coinNews.run {
        alias(feature)
        alias(library.compose)
    }
}

android {
    namespace = "com.rahim.coinnews.favorite.presentation"
}

dependencies{
    projects.run {
        implementation(favorite.domain)
    }
    libs.bundles.run{
        implementation(coil)
    }
}