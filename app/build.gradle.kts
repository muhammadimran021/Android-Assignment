plugins {
    id("com.android.application")
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
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName
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
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEV"
            isDebuggable = true
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
    api(project(":presentation-layer"))
    api(project(":domain-layer"))
    api(project(":data-layer"))

    api(Deps.kotlin)
    api(Deps.appCompat)
    api(Deps.materialDesign)
    api(Deps.constraintLayout)

    // Dependencies for Android Support
    api(Deps.materialDesign)
    api(Deps.constraintLayout)
    api(Deps.annotation)
    api(Deps.legacySupport)
    api(Deps.recyclerView)
    api(Deps.cardView)
    api(Deps.ktxCore)
    api(Deps.viewBinding)

    // Dependencies for Room
    api(Room.runtime)
    api(Room.ktx)
    kapt(Room.compiler)

    // Dependencies for Hilt
    api(Hilt.hilt)
    api(Hilt.hiltNavigationGraph)
    kapt(Hilt.hiltCompiler)
    kapt(Hilt.hiltAndroidXCompiler)

    // Dependencies for Glide
    api(Glide.glide)
    kapt(Glide.compiler)

    // Dependencies for Testing
    testApi(Testing.jUnit)
    androidTestApi(Testing.extJUnit)

    // Retrofit2
    api(Retrofit.retrofit)
    api(Retrofit.converterGson)
    api(Retrofit.interceptor)

    // Coroutines
    api(Coroutines.core)
    api(Coroutines.android)
    api(Coroutines.playService)

    // Gson
    api(Deps.gson)

    // Ktx
    api(Deps.fragmentKtx)

    // Paging
    api(Paging.paging)


    //ViewModel and LifeCycle
    api(ViewModelLifeCycle.lifeCycleCommonJava)
    api(ViewModelLifeCycle.lifCycleViewModel)
    api(ViewModelLifeCycle.lifeCycleLiveData)
    api(ViewModelLifeCycle.lifeCycleRuntime)

    //Navigation component
    api(Navigation.navigationFrg)
    api(Navigation.navigationUI)
    api(Navigation.navigationFrgKtx)
    api(Navigation.navigationUIKtx)
    api(Navigation.navigationDynFeature)


    //Intuit
    api(Intuit.sdp)
    api(Intuit.ssp)

    //Easypermissions
    api(EasyPermissions.easyPermissions)

}