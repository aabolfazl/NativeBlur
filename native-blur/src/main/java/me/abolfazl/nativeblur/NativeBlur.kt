/*
*
* Copyright (c) 2021 Abolfazl Abbasi
*
* */

package me.abolfazl.nativeblur

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import me.abolfazl.nativeblur.JNIWrapper.fastBlurAlpha
import kotlin.math.roundToInt

object NativeBlur {
    private const val SUCCESS = 1
    private const val INVALID_RADIUS = -1
    private const val CAN_NOT_GET_BITMAP_INFO = -2
    private const val INVALID_BITMAP_FORMAT = -3
    private const val BITMAP_CONCURRENCY_ERROR = -4

    /**
     * Returns a blurred bitmap with the specified radius and compress. Its
     * call native C++ method
     *
     * @param sourceBitmap    The source bitmap must blurred
     * @param radius   The radius of the blur grate than 1
     * @param compress   The compress config to return
     */
    fun blurBitmap(sourceBitmap: Bitmap, radius: Int = 10, compress: Boolean = true): Bitmap? {
        val startTime = System.currentTimeMillis()
        val result: Int
        val finalBitmap: Bitmap

        if (compress) {
            val compressedBitmap: Bitmap = if (sourceBitmap.height > sourceBitmap.width) {
                Bitmap.createBitmap(
                    (450f * sourceBitmap.width / sourceBitmap.height).roundToInt(),
                    450,
                    Bitmap.Config.ARGB_8888
                )
            } else {
                Bitmap.createBitmap(
                    450,
                    (450f * sourceBitmap.height / sourceBitmap.width).roundToInt(),
                    Bitmap.Config.ARGB_8888
                )
            }
            val paint = Paint(Paint.FILTER_BITMAP_FLAG)
            val rect = Rect(0, 0, compressedBitmap.width, compressedBitmap.height)
            Canvas(compressedBitmap).drawBitmap(sourceBitmap, null, rect, paint)
            result = fastBlurAlpha(compressedBitmap, radius)
            finalBitmap = compressedBitmap
        } else {
            result = fastBlurAlpha(sourceBitmap, radius)
            finalBitmap = sourceBitmap
        }

        if (BuildConfig.DEBUG) {
            logResult(result, startTime, radius, compress)
        }

        return if (result == 1) finalBitmap else null
    }

    /**
     * Log native JNI native call result in DEBUG
     * build config
     */
    private fun logResult(result: Int, startTime: Long, radius: Int, compress: Boolean) {
        val endTime = System.currentTimeMillis()
        when (result) {
            SUCCESS -> {
                Logger.info("Blur has done successfully with radius:$radius at:${endTime - startTime}ms in compress:$compress")
            }
            INVALID_RADIUS -> {
                Logger.error("INVALID_RADIUS $radius")
            }
            CAN_NOT_GET_BITMAP_INFO -> {
                Logger.error("CAN_NOT_GET_BITMAP_INFO")
            }
            INVALID_BITMAP_FORMAT -> {
                Logger.error("INVALID_BITMAP_FORMAT ARG888")
            }
            BITMAP_CONCURRENCY_ERROR -> {
                Logger.error("BITMAP_CONCURRENCY_ERROR")
            }
        }
    }
}