package me.abolfazl.nativeblur

import android.graphics.Bitmap

object JNIWrapper {
    private var initialized: Boolean = false
    private const val LIBRARY_NAME: String = "blurlib"

    init {
        try {
            System.loadLibrary(LIBRARY_NAME)
            initialized = true
        } catch (e: Exception) {
            Logger.error(e)
        }
    }

    /**
     * This native JNI method call for Stack Blur.
     * returns the blur Bitmap result in int.  Note that
     * while the unit of time of the return value is a error,
     *
     * @return Int result
     */
    external fun fastBlurAlpha(bitmap: Bitmap, radius: Int): Int
}