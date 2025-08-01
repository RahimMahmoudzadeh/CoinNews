package config

import org.gradle.api.JavaVersion

object Config {
  val android = AndroidConfig(
    minSdkVersion = 28,
    targetSdkVersion = 36,
    compileSdkVersion = 36,
    applicationId = "com.rahim.coinnews",
    versionCode = 1,
    versionName = "1.0.0",
    nameSpace = "com.rahim.coinnews",
  )
  val jvm = JvmConfig(
    javaVersion = JavaVersion.VERSION_17,
    kotlinJvm = JavaVersion.VERSION_17.toString(),
    freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn"),
  )
}
data class AndroidConfig(
  val minSdkVersion: Int,
  val targetSdkVersion: Int,
  val compileSdkVersion: Int,
  val applicationId: String,
  val versionCode: Int,
  val versionName: String,
  val nameSpace: String,
)
data class JvmConfig(
  val javaVersion: JavaVersion,
  val kotlinJvm: String,
  val freeCompilerArgs: List<String>,
)
