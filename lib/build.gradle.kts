/*
 * Copyright (C) 2021 Xizhi Zhu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.android.library")
//    id("com.github.dcendents.android-maven")
    kotlin("android")
}
apply("$rootDir/scripts/coverage.gradle.kts")

android {
    compileOptions {
        sourceCompatibility = Versions.java
        targetCompatibility = Versions.java
    }
    kotlinOptions {
        jvmTarget = Versions.Kotlin.jvmTarget
    }

    buildToolsVersion = Versions.Sdk.buildTools
    compileSdk = Versions.Sdk.compile

    defaultConfig {
        minSdk = Versions.Sdk.min
        targetSdk = Versions.Sdk.target
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("debug") {
            isTestCoverageEnabled = project.hasProperty("coverage")
        }
    }

    packagingOptions {
        exclude("META-INF/atomicfu.kotlin_module")
    }
}

tasks.withType(Test::class.java) {
    maxParallelForks = Runtime.getRuntime().availableProcessors() / 2
}

dependencies {
    api(Dependencies.AndroidX.annotation)
    compileOnly(Dependencies.Firebase.crashlytics)

    testImplementation(Dependencies.Kotlin.test)
    testImplementation(Dependencies.Mockk.mockk)
}
