plugins {
    libs.plugins.coinNews.run {
        alias(library)
        alias(network)
        alias(koin)
    }
}

android {
    namespace = "com.rahim.coinnews.home.data"
}
dependencies {
    projects.run {
//        implementation(core.db)
//        implementation(home.domain)
//        implementation(core.network)
//        implementation(core.utils)
    }
    libs.run {
        implementation(presianDate)
    }
}
