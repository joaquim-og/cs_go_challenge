plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.confradestech.csgochallenge"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.confradestech.csgochallenge"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            buildConfigField ("String", "BASE_URL", "\"https://api.pandascore.co/csgo/\"")
            buildConfigField ("String", "TOKEN", "'N__a8yh_5itmerXcg_ND3HYJY3u6CTvVNtWgRlYSekr8sGlw31E'")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            buildConfigField ("String", "BASE_URL", "\"https://api.pandascore.co/csgo/\"")
            buildConfigField ("String", "TOKEN", "\"N__a8yh_5itmerXcg_ND3HYJY3u6CTvVNtWgRlYSekr8sGlw31E\"")
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("com.google.gms:google-services:4.3.15")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation(platform("com.google.firebase:firebase-bom:31.2.3"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.25.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //analytics
    implementation("com.google.firebase:firebase-analytics-ktx")

    //Crashlytics
    implementation("com.google.firebase:firebase-crashlytics-ktx")

    //Kotlin Coroutines
    val coroutinesVersion = "1.6.4"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")

    //Jetpack Compose
    // Compose Material Design
    implementation("androidx.compose.material:material:1.3.1")
    // Animations
    implementation("androidx.compose.animation:animation:1.3.3")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0")

    //Gson
    implementation("com.google.code.gson:gson:2.9.1")

    //LeakCanary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.9.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("tech.thdev:flow-call-adapter-factory:1.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    //Coil
    implementation("io.coil-kt:coil-compose:1.3.1")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    // Splash screen Api
    implementation("androidx.core:core-splashscreen:1.0.0-beta02")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}