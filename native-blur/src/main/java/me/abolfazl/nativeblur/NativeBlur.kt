package me.abolfazl.nativeblur

import android.content.Context

object NativeBlur {

    var inited: Boolean = false
    private val LIBRARY_NAME: String = "blurlib"

    fun init(applicationContext: Context) {
        if (inited) {
            Logger.info("The module inited last time!")
            return
        }

        try {
            System.loadLibrary(LIBRARY_NAME)
            inited = true
        } catch (e: Exception) {
            Logger.error(e)
        }

        Logger.info(checkCPPLinker())
    }

    external fun checkCPPLinker(): String

}