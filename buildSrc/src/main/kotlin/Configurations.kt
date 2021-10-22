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

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.gradle.api.JavaVersion

object Configurations {
    val sampleAppId = "me.xizzhu.android.logger.sample"
}

object Versions {
    object Logger {
        const val code = 500
        val name: String by lazy {
            "${code / 10000}.${(code % 10000) / 100}.${code % 100} " +
                    "(${LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))})"
        }
    }

    object BinaryCompatibilityValidator {
        const val classpath = "0.7.1"
    }

    object Sdk {
        const val classpath = "7.0.3"
        const val buildTools = "31.0.0"
        const val compile = 31
        const val min = 21
        const val target = 31
    }

    val java = JavaVersion.VERSION_11

    object Kotlin {
        const val jvmTarget = "11"
        const val core = "1.5.31"
    }

    object AndroidX {
        const val annotation = "1.2.0"
    }

    object Firebase {
        const val crashlytics = "18.2.3"
    }

    const val mockk = "1.12.0"
}

object Dependencies {
    object Sdk {
        const val classpath = "com.android.tools.build:gradle:${Versions.Sdk.classpath}"
    }

    object BinaryCompatibilityValidator {
        const val classpath = "org.jetbrains.kotlinx:binary-compatibility-validator:${Versions.BinaryCompatibilityValidator.classpath}"
    }

    object Kotlin {
        const val classpath = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.core}"
        const val test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.Kotlin.core}"
    }

    object AndroidX {
        const val annotation = "androidx.annotation:annotation:${Versions.AndroidX.annotation}"
    }

    object Firebase {
        const val crashlytics = "com.google.firebase:firebase-crashlytics:${Versions.Firebase.crashlytics}"
    }

    object Mockk {
        const val mockk = "io.mockk:mockk:${Versions.mockk}"
    }
}
