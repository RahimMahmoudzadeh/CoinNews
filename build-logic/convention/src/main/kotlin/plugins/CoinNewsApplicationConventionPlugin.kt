package plugins

import applicationGradle
import applyPlugins
import config.Config
import configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import versionCatalog

class CoinNewsApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins {
                listOf(
                    "com.android.application",
                    "org.jetbrains.kotlin.android",
                )

            }
            applicationGradle {
                defaultConfig.apply {
                    targetSdk = Config.android.targetSdkVersion
                    applicationId = Config.android.applicationId
                    versionCode = Config.android.versionCode
                    versionName = Config.android.versionName
                    namespace = Config.android.applicationId
                }
                configureKotlinAndroid(this)
            }
            dependencies {
                val subprojects = project
                    .rootProject
                    .subprojects

                subprojects.filter { it.path.startsWith(":home:", false) }
                    .forEach { add("implementation", project(it.path)) }
                subprojects.filter { it.path.startsWith(":coindetail:", false) }
                    .forEach { add("implementation", project(it.path)) }
                subprojects.filter { it.path.startsWith(":favorite:", false) }
                    .forEach { add("implementation", project(it.path)) }
                subprojects.filter { it.path.startsWith(":core:", false) }
                    .forEach { add("implementation", project(it.path)) }

                subprojects.filter { it.path.startsWith(":library:", false) }
                    .forEach { add("implementation", project(it.path)) }
            }
        }
    }
}
