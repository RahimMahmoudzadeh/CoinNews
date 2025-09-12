plugins {
    libs.plugins.coinNews.run {
        alias(library)
        alias(network)
    }
}

android {
    namespace = "com.rahim.coinnews.coindetail.domain"
}
dependencies {
    projects.run {
        implementation(core.utils)
    }
}
