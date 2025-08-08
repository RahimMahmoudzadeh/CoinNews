plugins {
    libs.plugins.coinNews.run {
        alias(library)
        alias(network)
        alias(koin)
    }
}

android {
    namespace = "com.rahim.coinnews.core.network"
}
dependencies{
    projects.run {
        implementation(core.utils)
    }
}
