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

package me.xizzhu.android.logger

import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.*

class CrashlyticsLoggerTest {
    private lateinit var crashlytics: FirebaseCrashlytics
    private lateinit var logger: CrashlyticsLogger

    private val tag = "TAG"
    private val msg = "msg"
    private val exception = RuntimeException("Random exception")

    @BeforeTest
    fun setup() {
        crashlytics = mockk()
        logger = CrashlyticsLogger(crashlytics = crashlytics)
    }

    @Test
    fun `test log() without tag`() {
        every { crashlytics.log(msg) } returns Unit

        logger.log(Log.DEBUG, "", msg, null)
        verify(exactly = 1) { crashlytics.log(msg) }
    }

    @Test
    fun `test log() without tag, with exception`() {
        every { crashlytics.log(msg) } returns Unit
        every { crashlytics.recordException(exception) } returns Unit

        logger.log(Log.DEBUG, "", msg, exception)
        verify(exactly = 1) { crashlytics.log(msg) }
        verify(exactly = 1) { crashlytics.recordException(exception) }
    }

    @Test
    fun `test log() without msg`() {
        logger.log(Log.DEBUG, tag, "", null)
    }

    @Test
    fun `test log() without msg, with exception`() {
        every { crashlytics.recordException(exception) } returns Unit

        logger.log(Log.DEBUG, tag, "", exception)
        verify(exactly = 1) { crashlytics.recordException(exception) }
    }

    @Test
    fun `test log()`() {
        every { crashlytics.log("$tag: $msg") } returns Unit

        logger.log(Log.DEBUG, tag, msg, null)
        verify(exactly = 1) { crashlytics.log("$tag: $msg") }
    }

    @Test
    fun `test log() with exception`() {
        every { crashlytics.log("$tag: $msg") } returns Unit
        every { crashlytics.recordException(exception) } returns Unit

        logger.log(Log.DEBUG, tag, msg, exception)
        verify(exactly = 1) { crashlytics.log("$tag: $msg") }
        verify(exactly = 1) { crashlytics.recordException(exception) }
    }
}
