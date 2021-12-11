package me.abolfazl.nativeblur

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import kotlin.math.roundToInt

object NativeBlur {
    private var inited: Boolean = false
    private const val LIBRARY_NAME: String = "blurlib"

    init {
        try {
            System.loadLibrary(LIBRARY_NAME)
            inited = true
        } catch (e: Exception) {
            Logger.error(e)
        }
    }

    fun blurBitmap(sourceBitmap: Bitmap, radius: Int = 10, compress: Boolean = true): Bitmap? {
        if (!inited) {
            Logger.error("First init lib.")
            return null
        }
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

    private fun logResult(result: Int, startTime: Long, radius: Int, compress: Boolean) {
        val endTime = System.currentTimeMillis()
        when (result) {
            1 -> {
                Logger.info("Blur has done successfully with radios:$radius at:${endTime - startTime}ms in compress:$compress")
            }
            -1 -> {
                Logger.error("INVALID_RADIUS $radius")
            }
            -2 -> {
                Logger.error("CAN_NOT_GET_BITMAP_INFO")
            }
            -3 -> {
                Logger.error("INVALID_BITMAP_FORMAT ARG888")
            }
            -4 -> {
                Logger.error("BITMAP_CONCURRENCY_ERROR")
            }
        }
    }

    private external fun fastBlurAlpha(bitmap: Bitmap, radius: Int): Int

}