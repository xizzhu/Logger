[![Build Status](https://img.shields.io/github/workflow/status/xizzhu/Logger/Android%20CI/master?color=blue)](https://github.com/xizzhu/Logger/actions)
[![API](https://img.shields.io/badge/API-21%2B-green.svg?style=flat)](https://developer.android.com/about/versions/android-5.0.html)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)
[![JitPack](https://img.shields.io/jitpack/v/github/xizzhu/Logger.svg)](https://jitpack.io/#me.xizzhu/logger)

Logger
======

Yet another simple logger for your awesome Android application, written in Kotlin.

Download
--------
* Gradle: Add the following to your `build.gradle`:
```gradle
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'me.xizzhu:logger:$latest_version'
}
```

Usage
-----
### Initialize Logger
```kotlin
// Write logs to LogCat
Log.addLogger(LogcatLogger())

// Write logs to the specified file
Log.addLogger(FileLogger(logFile))

// Write logs to Firebase Crashlytics
Log.addLogger(CrashlyticsLogger())
```

#### Specify Log Level
When creating `Logger`s, the app can specify a log level, e.g.:
```kotlin
// Write logs to LogCat, only if they are equal or higher than DEBUG level
Log.addLogger(LogcatLogger(level = Log.DEBUG))
```

### Write Logs
To write logs, you can e.g.:
```kotlin
// Write logs in VERBOSE level
Log.v(tag, msg)

// Write logs with an exception in VERBOSE level
Log.v(tag, msg, exception)
```

If no `Logger` has been initialized, calling `Log.v()` and other logging methods are no-op.

License
-------
    Copyright (C) 2021 Xizhi Zhu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
