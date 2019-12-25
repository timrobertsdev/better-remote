plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kapt)
    id(BuildPlugins.navSafeArgsPlugin)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "us.timandkarli.simpleremote"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        buildToolsVersion(AndroidSdk.buildToolsVersion)
        versionCode = 1
        versionName = "0.1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.Coroutines.core)
    implementation(Libraries.Coroutines.android)
    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.legacySupport)
    implementation(Libraries.Lifecycle.extensions)
    implementation(Libraries.Lifecycle.viewmodel)
    implementation(Libraries.Navigation.fragment)
    implementation(Libraries.Navigation.ui)
    implementation(Libraries.Koin.core)
    implementation(Libraries.Koin.ext)
    implementation(Libraries.Koin.scope)
    implementation(Libraries.Koin.viewmodel)
    implementation(Libraries.retrofit)
    implementation(Libraries.Tikxml.annotation)
    implementation(Libraries.Tikxml.core)
    kapt(Libraries.Tikxml.processor)
    implementation(Libraries.Tikxml.retrofit)
    implementation(Libraries.Okhttp.okhttp)
    implementation(Libraries.Okhttp.logging)
    implementation(Libraries.Groupie.groupie)
    implementation(Libraries.Groupie.ktx)
    implementation(Libraries.Glide.glide)
    kapt(Libraries.Glide.compiler)
    implementation(Libraries.timber)
    debugImplementation(Libraries.leakCanary)
    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.extJunit)
    androidTestImplementation(TestLibraries.espresso)
}
