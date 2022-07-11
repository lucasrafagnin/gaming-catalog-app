import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 32

    val apiKey = gradleLocalProperties(rootDir).getProperty("RAWG_API_KEY")

    defaultConfig {
        applicationId = "com.rafagnin.gaming"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "0.1-alpha"

        buildConfigField("String", "RAWG_API_KEY", apiKey)
        buildConfigField("String", "RAWG_URL", "\"https://api.rawg.io/\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
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

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.moshi)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.room)

    implementation(libs.appCompat)
    implementation(libs.coil)
    implementation(libs.hilt)
    implementation(libs.liveData)
    implementation(libs.material)
    implementation(libs.paging)
    implementation(libs.retrofitMoshi)
    implementation(libs.viewModel)

    kapt(libs.hiltCompiler)
    kapt(libs.roomCompiler)
    testImplementation(libs.junit)

    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.junit)
}
