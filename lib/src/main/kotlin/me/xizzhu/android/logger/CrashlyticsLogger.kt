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

import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashlyticsLogger(@Log.Level override var level: Int = Log.VERBOSE) : Logger {
    private val crashlytics by lazy { FirebaseCrashlytics.getInstance() }

    override fun log(level: Int, tag: String, msg: String) {
        if (msg.isNotBlank()) {
            crashlytics.log("$tag: $msg")
        }
    }

    override fun log(level: Int, tag: String, msg: String, e: Throwable) {
        if (msg.isNotBlank()) {
            crashlytics.log("$tag: $msg")
        }
        crashlytics.recordException(e)
    }
}
