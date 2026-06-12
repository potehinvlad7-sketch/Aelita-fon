plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.artraccoon.aelitafon"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.artraccoon.aelitafon"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.1"
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2025.06.01"))
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.core:core-ktx:1.16.0")

    debugImplementation("androidx.compose.ui:ui-tooling")
}
