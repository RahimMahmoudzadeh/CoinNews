package plugins

import androidGradle
import applyPlugins
import configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import versionCatalog

class CoinNewsLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins {
                listOf(
                    versionCatalog.findPlugin("com.android.library").get().get().pluginId,
                    versionCatalog.findPlugin("kotlinAndroid").get().get().pluginId,
                    versionCatalog.findPlugin("kotlin-parcelize").get().get().pluginId,
                    versionCatalog.findPlugin("kotlinx-serialization").get().get().pluginId,
                )
            }
            androidGradle {
                configureKotlinAndroid(this)
            }
            dependencies {
                add(
                    "implementation",
                    versionCatalog.findLibrary("kotlinx-serialization").get()
                )
            }
        }
    }
}
