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
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class FileLogger(logFile: File,
                 @Log.Level override var level: Int = Log.VERBOSE,
                 private val executor: Executor = Executors.newSingleThreadExecutor()) : Logger {
    companion object {
        private val TAG = FileLogger::class.java.simpleName
    }

    private val fos: FileOutputStream by lazy { FileOutputStream(logFile, true) }
    private val dateFormat: SimpleDateFormat by lazy { SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US) }

    override fun log(@Log.Level level: Int, tag: String, msg: String, e: Throwable?) {
        executor.execute {
            try {
                synchronized(fos) {
                    fos.write(formatLog(level, tag, msg, e).toByteArray())
                    fos.flush()
                }
            } catch (e: Exception) {
                android.util.Log.e(TAG, "Failed to log to file", e)
            }
        }
    }

    private fun formatLog(@Log.Level level: Int, tag: String, msg: String, e: Throwable?): String {
        val sb = StringBuilder()
                .append(dateFormat.format(Date()))
                .append(' ')
                .append(when (level) {
                    Log.VERBOSE -> 'V'
                    Log.DEBUG -> 'D'
                    Log.INFO -> 'I'
                    Log.WARN -> 'W'
                    Log.ERROR -> 'E'
                    Log.FATAL -> 'F'
                    else -> throw IllegalArgumentException("Unsupported log level - $this")
                })
                .append('/')
                .append(tag)
                .append(": ")
                .append(msg)
                .append('\n')
        e?.let { sb.append(android.util.Log.getStackTraceString(it)).append('\n') }
        return sb.toString()
    }
}
