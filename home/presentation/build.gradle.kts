plugins {
    libs.plugins.coinNews.run {
        alias(feature)
        alias(library.compose)
    }
}

android {
    namespace = "com.rahim.coinnews.presentation"
}

dependencies{
    projects.run {
        implementation(home.domain)
        implementation(core.network)
    }
    libs.bundles.run{
        implementation(coil)
    }
}