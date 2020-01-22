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

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class LoggerTest {
    @Mock
    private lateinit var logger: Logger

    private val tag = "TAG"
    private val msg = "msg"
    private val exception = RuntimeException("Random exception")

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testNoLogger() {
        Log.v(tag, msg)
        verify(logger, never()).log(Log.VERBOSE, tag, msg)
        verify(logger, never()).log(Log.VERBOSE, tag, msg, exception)

        Log.d(tag, msg)
        verify(logger, never()).log(Log.DEBUG, tag, msg)
        verify(logger, never()).log(Log.DEBUG, tag, msg, exception)

        Log.i(tag, msg)
        verify(logger, never()).log(Log.INFO, tag, msg)
        verify(logger, never()).log(Log.INFO, tag, msg, exception)

        Log.w(tag, msg)
        verify(logger, never()).log(Log.WARN, tag, msg)
        verify(logger, never()).log(Log.WARN, tag, msg, exception)

        Log.e(tag, msg)
        verify(logger, never()).log(Log.ERROR, tag, msg)
        verify(logger, never()).log(Log.ERROR, tag, msg, exception)

        Log.f(tag, msg)
        verify(logger, never()).log(Log.FATAL, tag, msg)
        verify(logger, never()).log(Log.FATAL, tag, msg, exception)
    }

    @Test
    fun testNoLoggerWithException() {
        Log.v(tag, msg, exception)
        verify(logger, never()).log(Log.VERBOSE, tag, msg)
        verify(logger, never()).log(Log.VERBOSE, tag, msg, exception)

        Log.d(tag, msg, exception)
        verify(logger, never()).log(Log.DEBUG, tag, msg)
        verify(logger, never()).log(Log.DEBUG, tag, msg, exception)

        Log.i(tag, msg, exception)
        verify(logger, never()).log(Log.INFO, tag, msg)
        verify(logger, never()).log(Log.INFO, tag, msg, exception)

        Log.w(tag, msg, exception)
        verify(logger, never()).log(Log.WARN, tag, msg)
        verify(logger, never()).log(Log.WARN, tag, msg, exception)

        Log.e(tag, msg, exception)
        verify(logger, never()).log(Log.ERROR, tag, msg)
        verify(logger, never()).log(Log.ERROR, tag, msg, exception)

        Log.f(tag, msg, exception)
        verify(logger, never()).log(Log.FATAL, tag, msg)
        verify(logger, never()).log(Log.FATAL, tag, msg, exception)
    }

    @Test
    fun testAddThenRemoveLogger() {
        Log.addLogger(logger)
        Log.removeLogger(logger)

        Log.v(tag, msg)
        verify(logger, never()).log(Log.VERBOSE, tag, msg)
        verify(logger, never()).log(Log.VERBOSE, tag, msg, exception)

        Log.d(tag, msg)
        verify(logger, never()).log(Log.DEBUG, tag, msg)
        verify(logger, never()).log(Log.DEBUG, tag, msg, exception)

        Log.i(tag, msg)
        verify(logger, never()).log(Log.INFO, tag, msg)
        verify(logger, never()).log(Log.INFO, tag, msg, exception)

        Log.w(tag, msg)
        verify(logger, never()).log(Log.WARN, tag, msg)
        verify(logger, never()).log(Log.WARN, tag, msg, exception)

        Log.e(tag, msg)
        verify(logger, never()).log(Log.ERROR, tag, msg)
        verify(logger, never()).log(Log.ERROR, tag, msg, exception)

        Log.f(tag, msg)
        verify(logger, never()).log(Log.FATAL, tag, msg)
        verify(logger, never()).log(Log.FATAL, tag, msg, exception)
    }

    @Test
    fun testAddThenRemoveLoggerWithException() {
        Log.addLogger(logger)
        Log.removeLogger(logger)

        Log.v(tag, msg, exception)
        verify(logger, never()).log(Log.VERBOSE, tag, msg)
        verify(logger, never()).log(Log.VERBOSE, tag, msg, exception)

        Log.d(tag, msg, exception)
        verify(logger, never()).log(Log.DEBUG, tag, msg)
        verify(logger, never()).log(Log.DEBUG, tag, msg, exception)

        Log.i(tag, msg, exception)
        verify(logger, never()).log(Log.INFO, tag, msg)
        verify(logger, never()).log(Log.INFO, tag, msg, exception)

        Log.w(tag, msg, exception)
        verify(logger, never()).log(Log.WARN, tag, msg)
        verify(logger, never()).log(Log.WARN, tag, msg, exception)

        Log.e(tag, msg, exception)
        verify(logger, never()).log(Log.ERROR, tag, msg)
        verify(logger, never()).log(Log.ERROR, tag, msg, exception)

        Log.f(tag, msg, exception)
        verify(logger, never()).log(Log.FATAL, tag, msg)
        verify(logger, never()).log(Log.FATAL, tag, msg, exception)
    }

    @Test
    fun testAddLoggerTwice() {
        Log.addLogger(logger)
        Log.addLogger(logger)

        Log.v(tag, msg)
        verify(logger, times(1)).log(Log.VERBOSE, tag, msg)
        verify(logger, never()).log(Log.VERBOSE, tag, msg, exception)

        Log.d(tag, msg)
        verify(logger, times(1)).log(Log.DEBUG, tag, msg)
        verify(logger, never()).log(Log.DEBUG, tag, msg, exception)

        Log.i(tag, msg)
        verify(logger, times(1)).log(Log.INFO, tag, msg)
        verify(logger, never()).log(Log.INFO, tag, msg, exception)

        Log.w(tag, msg)
        verify(logger, times(1)).log(Log.WARN, tag, msg)
        verify(logger, never()).log(Log.WARN, tag, msg, exception)

        Log.e(tag, msg)
        verify(logger, times(1)).log(Log.ERROR, tag, msg)
        verify(logger, never()).log(Log.ERROR, tag, msg, exception)

        Log.f(tag, msg)
        verify(logger, times(1)).log(Log.FATAL, tag, msg)
        verify(logger, never()).log(Log.FATAL, tag, msg, exception)

        Log.removeLogger(logger)
    }

    @Test
    fun testAddLoggerTwiceWithException() {
        Log.addLogger(logger)
        Log.addLogger(logger)

        Log.v(tag, msg, exception)
        verify(logger, never()).log(Log.VERBOSE, tag, msg)
        verify(logger, times(1)).log(Log.VERBOSE, tag, msg, exception)

        Log.d(tag, msg, exception)
        verify(logger, never()).log(Log.DEBUG, tag, msg)
        verify(logger, times(1)).log(Log.DEBUG, tag, msg, exception)

        Log.i(tag, msg, exception)
        verify(logger, never()).log(Log.INFO, tag, msg)
        verify(logger, times(1)).log(Log.INFO, tag, msg, exception)

        Log.w(tag, msg, exception)
        verify(logger, never()).log(Log.WARN, tag, msg)
        verify(logger, times(1)).log(Log.WARN, tag, msg, exception)

        Log.e(tag, msg, exception)
        verify(logger, never()).log(Log.ERROR, tag, msg)
        verify(logger, times(1)).log(Log.ERROR, tag, msg, exception)

        Log.f(tag, msg, exception)
        verify(logger, never()).log(Log.FATAL, tag, msg)
        verify(logger, times(1)).log(Log.FATAL, tag, msg, exception)

        Log.removeLogger(logger)
    }

    @Test
    fun testLog() {
        Log.addLogger(logger)

        Log.v(tag, msg)
        verify(logger, times(1)).log(Log.VERBOSE, tag, msg)
        verify(logger, never()).log(Log.VERBOSE, tag, msg, exception)

        Log.d(tag, msg)
        verify(logger, times(1)).log(Log.DEBUG, tag, msg)
        verify(logger, never()).log(Log.DEBUG, tag, msg, exception)

        Log.i(tag, msg)
        verify(logger, times(1)).log(Log.INFO, tag, msg)
        verify(logger, never()).log(Log.INFO, tag, msg, exception)

        Log.w(tag, msg)
        verify(logger, times(1)).log(Log.WARN, tag, msg)
        verify(logger, never()).log(Log.WARN, tag, msg, exception)

        Log.e(tag, msg)
        verify(logger, times(1)).log(Log.ERROR, tag, msg)
        verify(logger, never()).log(Log.ERROR, tag, msg, exception)

        Log.f(tag, msg)
        verify(logger, times(1)).log(Log.FATAL, tag, msg)
        verify(logger, never()).log(Log.FATAL, tag, msg, exception)

        Log.removeLogger(logger)
    }

    @Test
    fun testLogWithException() {
        Log.addLogger(logger)

        Log.v(tag, msg, exception)
        verify(logger, never()).log(Log.VERBOSE, tag, msg)
        verify(logger, times(1)).log(Log.VERBOSE, tag, msg, exception)

        Log.d(tag, msg, exception)
        verify(logger, never()).log(Log.DEBUG, tag, msg)
        verify(logger, times(1)).log(Log.DEBUG, tag, msg, exception)

        Log.i(tag, msg, exception)
        verify(logger, never()).log(Log.INFO, tag, msg)
        verify(logger, times(1)).log(Log.INFO, tag, msg, exception)

        Log.w(tag, msg, exception)
        verify(logger, never()).log(Log.WARN, tag, msg)
        verify(logger, times(1)).log(Log.WARN, tag, msg, exception)

        Log.e(tag, msg, exception)
        verify(logger, never()).log(Log.ERROR, tag, msg)
        verify(logger, times(1)).log(Log.ERROR, tag, msg, exception)

        Log.f(tag, msg, exception)
        verify(logger, never()).log(Log.FATAL, tag, msg)
        verify(logger, times(1)).log(Log.FATAL, tag, msg, exception)

        Log.removeLogger(logger)
    }
}
