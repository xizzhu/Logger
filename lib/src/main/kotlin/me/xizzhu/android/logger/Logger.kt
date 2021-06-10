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

import androidx.annotation.IntDef
import java.util.concurrent.CopyOnWriteArrayList

interface Logger {
    @Log.Level
    var level: Int

    fun log(@Log.Level level: Int, tag: String, msg: String)

    fun log(@Log.Level level: Int, tag: String, msg: String, e: Throwable)
}

object Log {
    const val VERBOSE = 1
    const val DEBUG = 2
    const val INFO = 3
    const val WARN = 4
    const val ERROR = 5
    const val FATAL = 6

    @IntDef(VERBOSE, DEBUG, INFO, WARN, ERROR, FATAL)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Level

    private val loggers = CopyOnWriteArrayList<Logger>()

    fun addLogger(logger: Logger) {
        loggers.addIfAbsent(logger)
    }

    fun removeLogger(logger: Logger) {
        loggers.remove(logger)
    }

    fun v(tag: String, msg: String) {
        log(VERBOSE, tag, msg)
    }

    fun v(tag: String, msg: String, e: Throwable) {
        log(VERBOSE, tag, msg, e)
    }

    fun d(tag: String, msg: String) {
        log(DEBUG, tag, msg)
    }

    fun d(tag: String, msg: String, e: Throwable) {
        log(DEBUG, tag, msg, e)
    }

    fun i(tag: String, msg: String) {
        log(INFO, tag, msg)
    }

    fun i(tag: String, msg: String, e: Throwable) {
        log(INFO, tag, msg, e)
    }

    fun w(tag: String, msg: String) {
        log(WARN, tag, msg)
    }

    fun w(tag: String, msg: String, e: Throwable) {
        log(WARN, tag, msg, e)
    }

    fun e(tag: String, msg: String) {
        log(ERROR, tag, msg)
    }

    fun e(tag: String, msg: String, e: Throwable) {
        log(ERROR, tag, msg, e)
    }

    fun f(tag: String, msg: String) {
        log(FATAL, tag, msg)
    }

    fun f(tag: String, msg: String, e: Throwable) {
        log(FATAL, tag, msg, e)
    }

    private fun log(@Level level: Int, tag: String, msg: String) {
        for (logger in loggers) {
            if (level >= logger.level) {
                logger.log(level, tag, msg)
            }
        }
    }

    private fun log(@Level level: Int, tag: String, msg: String, e: Throwable) {
        for (logger in loggers) {
            if (level >= logger.level) {
                logger.log(level, tag, msg, e)
            }
        }
    }
}
