package me.abolfazl.nativeblur

import android.util.Log

object Logger {

    private val TAG: String = "NativeBlur"

    fun info(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, msg)
        }
    }

    fun error(e: Exception) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "error: ", e)
        }
    }

    fun error(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "error: $msg")
        }
    }
}