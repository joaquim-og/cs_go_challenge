package com.confradestech.csgochallenge.utilities.extensions.exceptions

import com.confradestech.csgochallenge.BuildConfig
import com.google.firebase.crashlytics.FirebaseCrashlytics

/**
 * Post the exception on Firebase Crashlytics, setting the error keys accordingly
 */
fun Throwable.postException() {
    if (BuildConfig.BUILD_TYPE == "release") {
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.setCustomKey(
            FirebaseErrorKeys.EXCEPTION_MESSAGE.name, this.message ?: ""
        )
        crashlytics.setCustomKey(FirebaseErrorKeys.STACKTRACE.name, this.stackTraceToString())
        crashlytics.recordException(this)
    } else {
        print(this.localizedMessage)
        this.printStackTrace()
    }
}