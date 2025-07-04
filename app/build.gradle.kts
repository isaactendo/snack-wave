

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.tendo.snackwaves"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tendo.snackwaves"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //implementation(androidx.navigation:navigation-compose:2.7.7)
    // Feature module support for Fragments

    //val nav_version = ""
   // implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")
    //implementation(libs.androidx.benchmark.macro)
    //implementation(libs.my.library)

    implementation (libs.androidx.material.icons.core)
    implementation( libs.androidx.material.icons.extended)
    implementation (platform(libs.firebase.bom)) // Use latest
    implementation (libs.firebase.auth.ktx)
    implementation (libs.firebase.database.ktx) // Optional, for Realtime DB
    implementation(libs.play.services.location)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
//    implementation(libs.androidx.animation.core.lint)
    implementation(libs.androidx.animation.lint)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}