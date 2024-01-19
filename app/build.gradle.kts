plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.example.appsms"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appsms"
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
}

dependencies {

    // Room
    implementation ("android.arch.persistence.room:runtime:1.1.1")
    //noinspection GradleCompatible,GradleCompatible,GradleCompatible
    implementation ("androidx.room:room-runtime:2.6.1")
    annotationProcessor ("androidx.room:room-compiler:2.6.1")
    implementation ("android.arch.persistence.room:runtime:1.1.1")
    implementation ("android.arch.lifecycle:extensions:1.1.1")
    //noinspection LifecycleAnnotationProcessorWithJava8
//    kapt ("android.arch.lifecycle:compiler:1.1.1")
//    kapt "android.arch.persistence.room:compiler:1.0.0"
//    kapt ("android.arch.persistence.room:compiler:1.1.1")
    //noinspection GradleCompatible,GradleCompatible
    implementation ("androidx.room:room-guava:2.6.1")
    implementation ("androidx.room:room-guava:2.6.1")
    implementation("com.mohamadamin:persianmaterialdatetimepicker:1.2.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    /// Room

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    //noinspection LifecycleAnnotationProcessorWithJava8
    kapt ("androidx.lifecycle:lifecycle-compiler:2.7.0-rc02")
    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")


    implementation ("com.github.samanzamani:PersianDate:1.7.1")


//    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}