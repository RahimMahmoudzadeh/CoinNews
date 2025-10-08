plugins {
    libs.plugins.coinNews.run {
        alias(library)
        alias(network)
    }
}

android {
    namespace = "com.rahim.coinnews.favorite.domain"
}
dependencies {
    projects.run {
        implementation(core.utils)
    }
}
