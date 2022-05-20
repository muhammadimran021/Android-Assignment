plugins {
    id("com.android.library")
    kotlin("android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
}

android {
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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
    api(project(":data-layer"))
    api(Deps.kotlin)
    api(Deps.appCompat)
    api(Deps.materialDesign)
    api(Deps.constraintLayout)

    // Dependencies for Android Support
    api(Deps.annotation)
    api(Deps.legacySupport)
    api(Deps.recyclerView)
    api(Deps.cardView)
    api(Deps.ktxCore)
    api(Deps.viewBinding)
    api("androidx.legacy:legacy-support-v4:1.0.0")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

    // Dependencies for Testing
    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.extJUnit)


    // Dependencies for Hilt
    api(Hilt.hilt)
    api(Hilt.hiltNavigationGraph)
    kapt(Hilt.hiltCompiler)
    kapt(Hilt.hiltAndroidXCompiler)

    //Navigation component
    api(Navigation.navigationFrg)
    api(Navigation.navigationUI)
    api(Navigation.navigationFrgKtx)
    api(Navigation.navigationUIKtx)
    api(Navigation.navigationDynFeature)

    api(SplashScreen.splash)

    // Anko
    api(KotlinAnko.anko)

    // Ktx
    api(Deps.fragmentKtx)

    //ViewModel and LifeCycle
    api(ViewModelLifeCycle.lifeCycleCommonJava)
    api(ViewModelLifeCycle.lifCycleViewModel)
    api(ViewModelLifeCycle.lifeCycleLiveData)
    api(ViewModelLifeCycle.lifeCycleRuntime)

    //Intuit
    api(Intuit.sdp)
    api(Intuit.ssp)

    // Paging
    api(Paging.paging)

    // Coin for image Loading
    api(Coil.coil)

    // Slider for Images Slide
    api(Slider.slider)


}