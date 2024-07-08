plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("kotlin-parcelize")
}


android {
    namespace = "me.yiyou.suanguaming"
    compileSdk = 34

    defaultConfig {
        applicationId = "me.yiyou.suanguaming"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("com.google.code.gson:gson:2.10.1")

    // room
    implementation("androidx.room:room-ktx:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")

    // viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // dialogx
    implementation("com.kongzue.dialogx:DialogX:0.0.49")
    implementation("com.kongzue.dialogx.style:DialogXIOSStyle:0.0.49")

    // 农历
    implementation("net.time4j:time4j-android:4.8-2021a")

    // mmkv
    implementation("com.tencent:mmkv-static:1.3.5")

}
