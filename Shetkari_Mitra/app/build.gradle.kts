plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.Shetkari_Mitra"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.Shetkari_Mitra"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "2.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }
}

configurations.all {
    resolutionStrategy {
        force("androidx.lifecycle:lifecycle-runtime:2.6.2")
        force("androidx.lifecycle:lifecycle-process:2.6.2")
        force("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
        force("androidx.lifecycle:lifecycle-livedata:2.6.2")
        force("androidx.lifecycle:lifecycle-common:2.6.2")
        force("androidx.lifecycle:lifecycle-common-java8:2.6.2")
        force("androidx.fragment:fragment:1.6.2")
        force("androidx.activity:activity:1.8.2")
        force("androidx.core:core:1.12.0")
    }
}

dependencies {
    // AndroidX Core & UI
    implementation("androidx.core:core:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.activity:activity:1.8.2")
    implementation("androidx.fragment:fragment:1.6.2")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.preference:preference:1.2.1")

    // Material Design 3
    implementation("com.google.android.material:material:1.12.0")

    // Lifecycle (2.6.2 - guarantees ReportFragment$ActivityInitializationListener exists)
    implementation("androidx.lifecycle:lifecycle-runtime:2.6.2")
    implementation("androidx.lifecycle:lifecycle-process:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata:2.6.2")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.2")
    implementation("androidx.startup:startup-runtime:1.1.1")

    // Room (local database)
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // Location (FusedLocationProvider - no Maps SDK needed)
    implementation("com.google.android.gms:play-services-location:21.3.0")

    // OpenStreetMap (replaces Google Maps entirely)
    implementation("org.osmdroid:osmdroid-android:6.1.18")

    // Responsive dimensions
    implementation("com.intuit.sdp:sdp-android:1.1.0")

    // Network (for future backend)
    implementation("com.android.volley:volley:1.2.1")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
