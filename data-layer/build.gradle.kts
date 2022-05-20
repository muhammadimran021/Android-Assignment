plugins {
    id("com.android.library")
    kotlin("android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
//        versionCode = ConfigData.versionCode
//        versionName = ConfigData.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"" + ConfigData.REL_BASE_URL + "\"")
            buildConfigField("String", "AUTH_TOKEN", "\"" + ConfigData.AUTH_TOKEN + "\"")
        }
        getByName("debug") {
//            isDebuggable = true
            buildConfigField("String", "BASE_URL", "\"" + ConfigData.DEV_BASE_URL + "\"")
            buildConfigField("String", "AUTH_TOKEN", "\"" + ConfigData.AUTH_TOKEN + "\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // other modules
    api(project(":domain-layer"))

    api(Deps.kotlin)
    api(Deps.appCompat)
    api(Deps.materialDesign)
    api(Deps.constraintLayout)

    // Dependencies for Android Support
    api(Deps.annotation)
    api(Deps.legacySupport)
    api(Deps.ktxCore)


    // Dependencies for Testing
    testApi(Testing.jUnit)
    androidTestApi(Testing.extJUnit)

    // Dependencies for Hilt
    api(Hilt.hilt)
    api(Hilt.hiltNavigationGraph)
    kapt(Hilt.hiltCompiler)
    kapt(Hilt.hiltAndroidXCompiler)

    // Retrofit2
    api(Retrofit.retrofit)
    api(Retrofit.converterGson)
    api(Retrofit.interceptor)

    // Coroutines
    api(Coroutines.core)
    api(Coroutines.android)
    api(Coroutines.playService)

    // Paging
    api(Paging.paging)
}