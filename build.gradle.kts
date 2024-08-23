import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {



    repositories {
        google()
        mavenCentral()
    }

//    kotlin_version ("1.3.10")
    dependencies {
        // Hilt Gradle Plugin for dependency injection
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("com.android.tools.build:gradle:8.2.1")
    }
}

plugins {
    // Android application plugin
    id("com.android.application") version "8.2.1" apply false
    // Kotlin plugin for Android development
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    // Kotlin Symbol Processing (KSP) plugin
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
//    id("kotlin-android-extensions") version "1.8.0" apply false
}


//googleKsp = "1.9.23-1.0.20"
//kotlin = "1.9.23"
//agp = "8.5.0-alpha08"

