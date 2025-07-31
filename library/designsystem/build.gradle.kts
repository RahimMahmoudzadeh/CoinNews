plugins {
    libs.plugins.coinNews.run {
        alias(library)
        alias(library.compose)
    }
}

android {
    namespace = "com.rahim.coinnews.library.designsystem"
}
dependencies{
    projects.run {
        implementation(core.utils)
    }
}

