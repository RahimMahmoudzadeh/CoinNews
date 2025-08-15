plugins {
    libs.plugins.coinNews.run {
        alias(library)
        alias(network)
        alias(koin)
    }
}

android {
    namespace = "com.rahim.coinnews.home.domain"
}
dependencies {
    projects.run {
        implementation(core.utils)
    }
}
