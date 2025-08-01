package plugins

import applyPlugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import versionCatalog

class CoinNewsFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugins {
                listOf("coinNews.library", "coinNews.koin")
            }
            dependencies {
                val subprojects = project
                    .rootProject
                    .subprojects

                add("implementation", versionCatalog.findLibrary("androidx.tracing.ktx").get())
                add("implementation",project(":core:utils"))
                subprojects.filter { it.path.startsWith(":library:", false) }
                    .forEach { add("implementation", project(it.path)) }
            }
        }
    }
}
