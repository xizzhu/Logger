/*
 * Copyright (C) 2019 Xizhi Zhu
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

interface Logger {
    fun log(@Log.Level level: Int, tag: String, msg: String)

    fun log(@Log.Level level: Int, tag: String, e: Throwable, msg: String)
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

    private val loggers: ArrayList<Logger> = ArrayList()

    fun addLogger(logger: Logger) {
        if (!loggers.contains(logger)) {
            loggers.add(logger)
        }
    }

    fun removeLogger(logger: Logger) {
        loggers.remove(logger)
    }

    fun v(tag: String, msg: String) {
        log(VERBOSE, tag, msg)
    }

    fun v(tag: String, e: Throwable, msg: String) {
        log(VERBOSE, tag, e, msg)
    }

    fun d(tag: String, msg: String) {
        log(DEBUG, tag, msg)
    }

    fun d(tag: String, e: Throwable, msg: String) {
        log(DEBUG, tag, e, msg)
    }

    fun i(tag: String, msg: String) {
        log(INFO, tag, msg)
    }

    fun i(tag: String, e: Throwable, msg: String) {
        log(INFO, tag, e, msg)
    }

    fun w(tag: String, msg: String) {
        log(WARN, tag, msg)
    }

    fun w(tag: String, e: Throwable, msg: String) {
        log(WARN, tag, e, msg)
    }

    fun e(tag: String, msg: String) {
        log(ERROR, tag, msg)
    }

    fun e(tag: String, e: Throwable, msg: String) {
        log(ERROR, tag, e, msg)
    }

    fun f(tag: String, msg: String) {
        log(FATAL, tag, msg)
    }

    fun f(tag: String, e: Throwable, msg: String) {
        log(FATAL, tag, e, msg)
    }

    private fun log(@Level level: Int, tag: String, msg: String) {
        for (logger in loggers) {
            logger.log(level, tag, msg)
        }
    }

    private fun log(@Level level: Int, tag: String, e: Throwable, msg: String) {
        for (logger in loggers) {
            logger.log(level, tag, e, msg)
        }
    }
}
