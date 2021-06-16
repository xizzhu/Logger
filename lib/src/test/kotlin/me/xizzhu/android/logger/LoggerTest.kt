/*
 * Copyright (C) 2020 Xizhi Zhu
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

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class LoggerTest {
    private lateinit var logger: Logger

    private val tag = "TAG"
    private val msg = "msg"
    private val exception = RuntimeException("Random exception")

    @BeforeTest
    fun setup() {
        logger = mockk()
        every { logger.log(any(), any(), any(), any()) } returns Unit
        every { logger.level } returns Log.VERBOSE
    }

    @Test
    fun testNoLogger() {
        Log.v(tag, msg)
        Log.d(tag, msg)
        Log.i(tag, msg)
        Log.w(tag, msg)
        Log.e(tag, msg)
        Log.f(tag, msg)

        verify(exactly = 0) {
            logger.log(any(), any(), any(), any())
        }
    }

    @Test
    fun testNoLoggerWithException() {
        Log.v(tag, msg, exception)
        Log.d(tag, msg, exception)
        Log.i(tag, msg, exception)
        Log.w(tag, msg, exception)
        Log.e(tag, msg, exception)
        Log.f(tag, msg, exception)

        verify(exactly = 0) {
            logger.log(any(), any(), any(), any())
        }
    }

    @Test
    fun testAddThenRemoveLogger() {
        Log.addLogger(logger)
        Log.removeLogger(logger)

        Log.v(tag, msg)
        Log.d(tag, msg)
        Log.i(tag, msg)
        Log.w(tag, msg)
        Log.e(tag, msg)
        Log.f(tag, msg)

        verify(exactly = 0) {
            logger.log(any(), any(), any(), any())
        }
    }

    @Test
    fun testAddThenRemoveLoggerWithException() {
        Log.addLogger(logger)
        Log.removeLogger(logger)

        Log.v(tag, msg, exception)
        Log.d(tag, msg, exception)
        Log.i(tag, msg, exception)
        Log.w(tag, msg, exception)
        Log.e(tag, msg, exception)
        Log.f(tag, msg, exception)

        verify(exactly = 0) {
            logger.log(any(), any(), any(), any())
        }
    }

    @Test
    fun testAddLoggerTwice() {
        Log.addLogger(logger)
        Log.addLogger(logger)

        Log.v(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.VERBOSE, tag, msg, null)
        }

        Log.d(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.DEBUG, tag, msg, null)
        }

        Log.i(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.INFO, tag, msg, null)
        }

        Log.w(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.WARN, tag, msg, null)
        }

        Log.e(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.ERROR, tag, msg, null)
        }

        Log.f(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.FATAL, tag, msg, null)
        }

        Log.removeLogger(logger)
    }

    @Test
    fun testAddLoggerTwiceWithException() {
        Log.addLogger(logger)
        Log.addLogger(logger)

        Log.v(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.VERBOSE, tag, msg, exception)
        }

        Log.d(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.DEBUG, tag, msg, exception)
        }

        Log.i(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.INFO, tag, msg, exception)
        }

        Log.w(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.WARN, tag, msg, exception)
        }

        Log.e(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.ERROR, tag, msg, exception)
        }

        Log.f(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.FATAL, tag, msg, exception)
        }

        Log.removeLogger(logger)
    }

    @Test
    fun testLog() {
        Log.addLogger(logger)

        Log.v(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.VERBOSE, tag, msg, null)
        }

        Log.d(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.DEBUG, tag, msg, null)
        }

        Log.i(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.INFO, tag, msg, null)
        }

        Log.w(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.WARN, tag, msg, null)
        }

        Log.e(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.ERROR, tag, msg, null)
        }

        Log.f(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.FATAL, tag, msg, null)
        }

        Log.removeLogger(logger)
    }

    @Test
    fun testLogWithException() {
        Log.addLogger(logger)

        Log.v(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.VERBOSE, tag, msg, exception)
        }

        Log.d(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.DEBUG, tag, msg, exception)
        }

        Log.i(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.INFO, tag, msg, exception)
        }

        Log.w(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.WARN, tag, msg, exception)
        }

        Log.e(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.ERROR, tag, msg, exception)
        }

        Log.f(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.FATAL, tag, msg, exception)
        }

        Log.removeLogger(logger)
    }

    @Test
    fun testLogLevel() {
        every { logger.level } returns Log.INFO
        Log.addLogger(logger)

        Log.v(tag, msg)
        verify(exactly = 0) {
            logger.log(Log.VERBOSE, any(), any(), any())
        }

        Log.d(tag, msg)
        verify(exactly = 0) {
            logger.log(Log.DEBUG, any(), any(), any())
        }

        Log.i(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.INFO, tag, msg, null)
        }

        Log.w(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.WARN, tag, msg, null)
        }

        Log.e(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.ERROR, tag, msg, null)
        }

        Log.f(tag, msg)
        verify(exactly = 1) {
            logger.log(Log.FATAL, tag, msg, null)
        }

        Log.removeLogger(logger)
    }

    @Test
    fun testLogLevelWithException() {
        every { logger.level } returns Log.WARN
        Log.addLogger(logger)

        Log.v(tag, msg, exception)
        verify(exactly = 0) {
            logger.log(Log.VERBOSE, any(), any(), any())
        }

        Log.d(tag, msg, exception)
        verify(exactly = 0) {
            logger.log(Log.DEBUG, any(), any(), any())
        }

        Log.i(tag, msg, exception)
        verify(exactly = 0) {
            logger.log(Log.INFO, any(), any(), any())
        }

        Log.w(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.WARN, tag, msg, exception)
        }

        Log.e(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.ERROR, tag, msg, exception)
        }

        Log.f(tag, msg, exception)
        verify(exactly = 1) {
            logger.log(Log.FATAL, tag, msg, exception)
        }

        Log.removeLogger(logger)
    }
}
