plugins {
    libs.plugins.coinNews.run {
        alias(library)
        alias(network)
        alias(koin)
    }
}

android {
    namespace = "com.rahim.coinnews.coindetail.data"
}
dependencies {
    projects.run {
        implementation(coindetail.domain)
        implementation(core.network)
        implementation(core.utils)
    }
}