plugins {
    libs.plugins.coinNews.run {
        alias(library)
        alias(koin)
        alias(room)
    }
}

android {
    namespace = "com.rahim.coinnews.favorit.data"
}
dependencies {
    projects.run {
        implementation(favorite.domain)
        implementation(core.utils)
        implementation(core.db)
    }
}
