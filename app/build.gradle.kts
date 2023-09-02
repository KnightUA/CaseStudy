plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "ua.shtest.casestudy"
    compileSdk = 34

    defaultConfig {
        applicationId = "ua.shtest.casestudy"
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val dagger_version = "2.46.1"
    val gson_version = "2.9.0"
    val okhttp_version = "4.11.0"
    val retrofit_version = "2.9.0"
    val rxandroid_version = "3.0.2"
    val rxjava_version = "3.1.5"
    val nav_version = "2.7.1"
    val room_version = "2.5.2"
    val lifecycle_version = "2.6.1"
    val timber_version = "5.0.1"

    // Basic
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    // Dagger
    implementation("com.google.dagger:dagger:$dagger_version")
    kapt("com.google.dagger:dagger-compiler:$dagger_version")

    // Gson
    implementation("com.google.code.gson:gson:$gson_version")

    // Okhttp
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp_version")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-scalars:$retrofit_version")

    // RxJava
    implementation("io.reactivex.rxjava3:rxandroid:$rxandroid_version")
    implementation("io.reactivex.rxjava3:rxjava:$rxjava_version")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Room
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    // Logging
    implementation("com.jakewharton.timber:timber:$timber_version")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}