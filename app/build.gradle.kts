plugins {
    id("com.android.application")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.hieult.foodhub"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hieult.foodhub"
        minSdk = 24
        targetSdk = 33
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
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.5")
    implementation("androidx.navigation:navigation-ui:2.7.5")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //google firebase
    implementation ("com.firebaseui:firebase-ui-database:8.0.2")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-database-ktx:20.3.0")
    implementation("com.google.firebase:firebase-auth-ktx:22.2.0")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    //facebook
    implementation ("com.facebook.android:facebook-android-sdk:16.2.0")

    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.0")
    //gilde
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.readystatesoftware.sqliteasset:sqliteassethelper:+")
    //Stripe dependency
    implementation ("com.stripe:stripe-android:20.34.4")
    // sor network call
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation ("com.google.code.gson:gson:2.9.0")
    implementation ("com.android.volley:volley:1.2.1")
    //Map-box
    implementation ("com.mapbox.mapboxsdk:mapbox-android-sdk:9.2.1")
    implementation ("ai.nextbillion:nb-maps-android:1.0.2")
    implementation ("ai.nextbillion:nb-navigation-android:1.1.5")
    implementation ("com.google.android.gms:play-services-maps:18.2.0")

    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("com.makeramen:roundedimageview:2.3.0")

}
