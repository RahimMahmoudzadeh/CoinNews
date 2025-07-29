import convention.CoinNewsBuildType

plugins {
    libs.plugins.coinNews.run {
        alias(application)
        alias(application.compose)
        alias(koin)
        alias(decompose)
    }
}
android {
    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        debug {
            applicationIdSuffix = CoinNewsBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            applicationIdSuffix = CoinNewsBuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}
dependencies {
    libs.run {
        implementation(androidx.core.splashscreen)
        implementation(androidx.constraintlayout)
        implementation(kotlinx.collections.immutable)
    }
}