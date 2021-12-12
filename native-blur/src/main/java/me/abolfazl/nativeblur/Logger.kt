/*
*
* Copyright (c) 2021 Abolfazl Abbasi
*
* */

package me.abolfazl.nativeblur

import android.util.Log

object Logger {

    private const val TAG: String = "NativeBlurLog"

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