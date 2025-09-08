plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt") // to use kapt
}

android {
    namespace = "com.example.tmsxmlproject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tmsxmlproject"
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

    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //dagger2
    implementation("com.google.dagger:dagger:2.48.1")
    implementation("com.google.dagger:dagger-android:2.48.1")
    implementation("com.google.dagger:dagger-android-support:2.48.1")
    kapt("com.google.dagger:dagger-compiler:2.48.1")
    kapt("com.google.dagger:dagger-android-processor:2.48.1")

    //dataStore (sharedPrefs doesn't use deps)
    implementation("androidx.datastore:datastore-preferences:1.1.7")

    // room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    //leak canary
    implementation("com.squareup.leakcanary:leakcanary-android:2.14")

    //gson
    implementation("com.google.code.gson:gson:2.8.8")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //gson to convert(serialize) api response to our kotlin data models
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //api logger
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //glide - for images
    implementation("com.github.bumptech.glide:glide:4.15.1")
    kapt("com.github.bumptech.glide:compiler:4.15.1")

    // viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.2")

    // Lifecycle Scope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")

    // Activity KTX (for by viewModels())
    implementation("androidx.activity:activity-ktx:1.10.1")

    //Fragment
    implementation(libs.fragmentByViewModels)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}