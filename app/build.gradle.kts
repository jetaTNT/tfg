plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.apptfg"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.apptfg"
        minSdk = 35
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
}

dependencies {
    // AndroidX Core
    implementation("androidx.core:core-ktx:1.9.0")

    // AppCompat y Material Components
    implementation(libs.appcompat)
    implementation(libs.material)

    // Activity KTX (si lo necesitas)
    implementation(libs.activity)

    // ConstraintLayout
    implementation(libs.constraintlayout)

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.0")

    // Lifecycle (LiveData + ViewModel)
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // Gson para JSON
    implementation("com.google.code.gson:gson:2.10.1")

    // Room (runtime + compiler + KTX)
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // SwipeRefreshLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}