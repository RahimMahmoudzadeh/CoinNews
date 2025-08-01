plugins {
    libs.plugins.coinNews.run {
        alias(library)
        alias(library.compose)
        alias(decompose)
    }
}

android {
    namespace = "com.rahim.coinnews.library.navigation"
}
dependencies{
    implementation(projects.core.utils)
    implementation(projects.library.designsystem)
}
