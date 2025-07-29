import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
  `kotlin-dsl`
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_17
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.android.tools.common)
  compileOnly(libs.compose.gradlePlugin)
  compileOnly(libs.firebase.crashlytics.gradlePlugin)
  compileOnly(libs.firebase.performance.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.ksp.gradlePlugin)
  compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
  plugins {
    register("coinNewsApplicationCompose") {
      id = "coinNews.application.compose"
      implementationClass = "plugins.CoinNewsApplicationComposeConventionPlugin"
    }
    register("coinNewsApplication") {
      id = "coinNews.application"
      implementationClass = "plugins.CoinNewsApplicationConventionPlugin"
    }
    register("coinNewsLibraryCompose") {
      id = "coinNews.library.compose"
      implementationClass = "plugins.CoinNewsLibraryComposeConventionPlugin"
    }
    register("coinNewsLibrary") {
      id = "coinNews.library"
      implementationClass = "plugins.CoinNewsLibraryConventionPlugin"
    }
    register("coinNewsFeature") {
      id = "coinNews.feature"
      implementationClass = "plugins.CoinNewsFeatureConventionPlugin"
    }
    register("coinNewsKoin") {
      id = "coinNews.koin"
      implementationClass = "plugins.CoinNewsKoinConventionPlugin"
    }
    register("coinNewsNetwork") {
      id = "coinNews.network"
      implementationClass = "plugins.CoinNewsNetworkConventionPlugin"
    }
    register("coinNewsRoom") {
      id = "coinNews.room"
      implementationClass = "plugins.CoinNewsRoomConventionPlugin"
    }
    register("coinNewsFirebase") {
      id = "coinNews.application.firebase"
      implementationClass = "plugins.CoinNewsApplicationFirebaseConventionPlugin"
    }
    register("coinNewsImageLoader") {
      id = "coinNews.image.loader"
      implementationClass = "plugins.CoinNewsImageLoaderConventionPlugin"
    }
    register("coinNewsDecompose") {
      id = "coinNews.decompose"
      implementationClass = "plugins.CoinNewsDecomposeConventionPlugin"
    }
  }
}
