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

import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.*

class FileLoggerTest {
    private lateinit var logFile: File
    private lateinit var logger: FileLogger

    private val tag = "TAG"
    private val msg = "msg"
    private val exception = RuntimeException("Random exception")

    @BeforeTest
    fun setup() {
        logFile = File.createTempFile("test_", ".log")
        logger = FileLogger(logFile = logFile, executor = { it.run() })
    }

    @Test
    fun testLog() {
        logger.log(Log.VERBOSE, tag, msg, null)
        logger.log(Log.DEBUG, tag, msg, null)
        logger.log(Log.INFO, tag, msg, null)
        logger.log(Log.WARN, tag, msg, null)
        logger.log(Log.ERROR, tag, msg, null)
        logger.log(Log.FATAL, tag, msg, null)

        logFile.forEachLineIndexed { index, line ->
            // make sure the first part is a valid timestamp
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US).parse(line.substring(0, 23))

            val level = when (index) {
                0 -> 'V'
                1 -> 'D'
                2 -> 'I'
                3 -> 'W'
                4 -> 'E'
                5 -> 'F'
                else -> fail()
            }
            assertTrue(line.endsWith(" $level/TAG: msg"))
        }
    }

    @Test
    fun testLogWithException() {
        logger.log(Log.VERBOSE, tag, msg, exception)
        logger.log(Log.DEBUG, tag, msg, exception)
        logger.log(Log.INFO, tag, msg, exception)
        logger.log(Log.WARN, tag, msg, exception)
        logger.log(Log.ERROR, tag, msg, exception)
        logger.log(Log.FATAL, tag, msg, exception)

        logFile.forEachLineIndexed { index, line ->
            if (index % 2 != 0) {
                assertEquals(exception.message, line)
                return@forEachLineIndexed
            }

            // make sure the first part is a valid timestamp
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US).parse(line.substring(0, 23))

            val level = when (index) {
                0 -> 'V'
                2 -> 'D'
                4 -> 'I'
                6 -> 'W'
                8 -> 'E'
                10 -> 'F'
                else -> fail()
            }
            assertTrue(line.endsWith(" $level/TAG: msg"))
        }
    }

    private fun File.forEachLineIndexed(action: (index: Int, line: String) -> Unit) {
        var index = 0
        forEachLine { action(index++, it) }
    }
}
