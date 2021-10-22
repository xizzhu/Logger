CHANGELOG
---------

#### 0.6.1
- Changes:
  - Allow app to provide FirebaseCrashlytics instance

#### 0.6.0
- Changes:
  - Use GitHub as CI
  - Artifact group ID changed to me.xizzhu
- Updated dependencies:
  - Gradle to 7.0.2
  - Java and Kotlin JVM Target to 11
  - Android Gradle plugin to 7.0.3
  - Android Build SDK to 31.0.0, Compile and Target to 31
  - Kotlin to 1.5.31
  - Firebase Crashlytics to 18.2.3
  - Mockk to 1.12.0

#### 0.5.0
- New features:
  - Support log level
- Changes:
  - Simplify Logger interface by merging the two log() functions
  - Use mockk instead of Mockito
  - Remove jcenter() from repository dependencies
- Updated dependencies:
  - Gradle to 6.9
  - Android Gradle plugin to 4.2.1
  - Kotlin to 1.5.10
  - AndroidX Annotation to 1.2.0
  - Firebase Crashlytics to 18.0.1 

#### 0.4.1
- Bug fixes:
  - Should do `executor.execute()` instead of `run()` (#4)

#### 0.4.0
- New features:
  - Add CrashlyticsLogger
- Updated dependencies:
  - Gradle to 6.7.1
  - Android Gradle plugin to 4.1.1
  - Android build tool to 30.0.3
  - Kotlin to 1.4.21
  - Mockito to 3.6.28

#### 0.3.0
- Bug fixes:
  - Use internal file dir for sample app
  - Use CopyOnWriteArrayList to store list of loggers
- Updated dependencies:
  - Gradle to 6.6.1
  - Android Gradle plugin to 4.0.1
  - Android build tool to 30.0.2
  - Compile and target SDK to 30
  - Kotlin to 1.4.0
  - Mockito to 3.5.9

#### 0.2.0
- Used Executor instead of coroutine for FileLogger
- Updated dependencies:
  - Gradle to 6.2.2
  - Android Gradle plugin to 3.6.1
  - Android build tool to 29.0.3
  - Kotlin to 1.3.70
  - Mockito to 3.3.0

#### 0.1.5
- Updated dependencies:
  - Gradle to 6.0.1
  - Android Gradle plugin to 3.5.3
  - Kotlin to 1.3.61
  - Kotlin Coroutine to 1.3.1
  - Mockito to 3.2.0

#### 0.1.4
- Updated dependencies:
  - Kotlin Coroutine to 1.3.0

#### 0.1.3
- Updated dependencies:
  - Gradle to 5.6
  - Android Gradle plugin to 3.5.0
  - Android build tool to 29.0.2
  - Kotlin to 1.3.50
  - Mockito to 3.0.0

#### 0.1.2
- Updated dependencies:
  - Gradle to 5.5
  - Kotlin to 1.3.41
  - Kotlin Coroutine to 1.2.2

#### 0.1.1
- Bumped Kotlin to 1.3.40

#### 0.1.0
- Initial release
