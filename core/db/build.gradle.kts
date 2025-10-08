plugins {
    libs.plugins.coinNews.run {
        alias(library)
        alias(koin)
        alias(room)
    }
}
android {
    namespace = "com.rahim.coinnews.core.db"
}
dependencies{
    libs.run {
    }
}