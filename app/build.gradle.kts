plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.aston_final_project"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.aston_final_project"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.glide)

    implementation(libs.androidx.room.rxjava3)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    implementation(libs.moxy)
    implementation(libs.moxy.androidx)
    implementation(libs.moxy.material)
    kapt(libs.moxy.compiler)

    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation(libs.adapter.rxjava3)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}