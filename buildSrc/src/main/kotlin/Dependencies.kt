const val kotlinVersion = "1.3.61"
const val navigation = "2.1.0"

object BuildPlugins {

    object Versions {
        const val androidGradlePlugin = "3.5.3"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kapt = "kotlin-kapt"
    const val navSafeArgsPlugin = "androidx.navigation.safeargs"
    const val navSafeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:$navigation"
}

object AndroidSdk {
    const val min = 21
    const val compile = 29
    const val target = compile
    const val buildToolsVersion = "29.0.2"
}

object Libraries {
    private object Versions {
        const val appCompat = "1.1.0"
        const val ktxCore = "1.1.0"
        const val coroutines = "1.3.0"
        const val constraintLayout = "1.1.3"
        const val legacySupport = "1.0.0"
        const val lifecycle = "2.1.0"
        const val koin = "2.0.1"
        const val retrofit = "2.6.3"
        const val okhttp = "4.2.2"
        const val tikxml = "master-SNAPSHOT"
        const val groupie = "2.7.2"
        const val glide = "4.10.0"
        const val timber = "4.7.1"
        const val leakCanary = "2.0"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Lifecycle {
        const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }

    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$navigation"
        const val ui = "androidx.navigation:navigation-ui-ktx:$navigation"
    }

    object Koin {
        const val core = "org.koin:koin-core:${Versions.koin}"
        const val ext = "org.koin:koin-core-ext:${Versions.koin}"
        const val scope = "org.koin:koin-androidx-scope:${Versions.koin}"
        const val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    }

    object Tikxml {
        const val core = "com.github.tickaroo.tikxml:core:${Versions.tikxml}"
        const val annotation = "com.github.tickaroo.tikxml:annotation:${Versions.tikxml}"
        const val processor = "com.github.tickaroo.tikxml:processor:${Versions.tikxml}"
        const val processorCommon = "com.github.tickaroo.tikxml:processor-common:${Versions.tikxml}"
        const val retrofit = "com.github.tickaroo.tikxml:retrofit-converter:${Versions.tikxml}"
    }

    object Okhttp {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Groupie {
        const val groupie = "com.xwray:groupie:${Versions.groupie}"
        const val ktx = "com.xwray:groupie-kotlin-android-extensions:${Versions.groupie}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val extJunit = "1.1.1"
        const val espresso = "3.2.0"
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}