package io.volgadev.sampleapp.project

const val kotlinVersion = "1.6.21"

object Dependencies {

    object AndroidX {
        const val activity = "androidx.activity:activity-ktx:1.4.0"

        object Compose {
            const val composeCompilerVersion = "1.3.2"
            const val composeVersion = "1.3.1"

            const val ui = "androidx.compose.ui:ui:$composeVersion"
            const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
            const val toolingPreview = "androidx.compose.ui:ui-tooling:$composeVersion"
            const val compiler = "androidx.compose.compiler:compiler:$composeCompilerVersion"
            const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
            const val activity = "androidx.activity:activity-compose:1.4.0"
            const val material = "androidx.compose.material:material:$composeVersion"
            const val materialIcons =
                "androidx.compose.material:material-icons-extended:$composeVersion"

            const val coil = "io.coil-kt:coil-compose:2.0.0-rc01"
            const val themeAdapter = "com.google.android.material:compose-theme-adapter:1.1.4"
        }

        object Accompanist {
            private const val accompanistVersion = "0.24.9-beta"
            const val insets = "com.google.accompanist:accompanist-insets:$accompanistVersion"
            const val accompanistPlaceholder =
                "com.google.accompanist:accompanist-placeholder:${accompanistVersion}"
        }

        object Lifecycle {
            private const val version = "2.4.1"

            const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val processLifeCycle = "androidx.lifecycle:lifecycle-process:$version"
            const val viewModelKotlinExt = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
        }

        object Core {
            const val coreKtx = "androidx.core:core-ktx:1.6.0"
        }
    }

    object Api {

        object Ktor {
            const val version = "1.5.0"
            const val core = "io.ktor:ktor-client-android:$version"
            const val logging = "io.ktor:ktor-client-logging:$version"
        }
    }

    object Kotlin {
        const val std = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

        object Serialization {
            private const val version = "1.3.2"
            const val ktor = "io.ktor:ktor-client-serialization:${Api.Ktor.version}"
            const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
        }

        object Coroutines {
            private const val version = "1.6.0"

            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }
    }

    object Logger {
        const val core = "com.jakewharton.timber:timber:5.0.1"
    }

    object Koin {
        private const val version = "3.2.0"
        const val core = "io.insert-koin:koin-core:$version"
        const val android = "io.insert-koin:koin-android:$version"
        const val compose = "io.insert-koin:koin-androidx-compose:$version"

    }

    object Navigation {
        private const val version = "2.4.2"
        val navigationCompose = "androidx.navigation:navigation-compose:$version"
    }

}
