plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.lalilu.utils.fix14bug"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        externalNativeBuild {
            cmake {
                cppFlags += "-fvisibility=hidden"
                arguments += listOf("-DANDROID_ARM_MODE=arm", "-DANDROID_STL=c++_static")
            }
        }

        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a")
        }
    }
    buildFeatures {
        prefab = true
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
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packaging {
        jniLibs.excludes += "**/libshadowhook.so"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.startup:startup-runtime:1.1.1")
    implementation("com.bytedance.android:shadowhook:1.0.10")
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("release") {
                from(components.getByName("release"))
                groupId = "com.github.cy745"
                artifactId = "Fix14Bug"
                version = "0.0.1"
            }
        }
    }
}