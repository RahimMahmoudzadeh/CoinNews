plugins {
    libs.plugins.coinNews.run {
        alias(library)
        alias(library.compose)
        alias(decompose)
        alias(network)
    }
}

android {
    namespace = "ir.stap.core.utils"
}
dependencies{
    implementation(libs.presianDate)
    implementation(libs.kotlin.reflect)
}
