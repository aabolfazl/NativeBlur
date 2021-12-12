/*
*
* Copyright (c) 2021 Abolfazl Abbasi
*
* */

package me.abolfazl.nativeblur.view

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import me.abolfazl.nativeblur.Logger
import me.abolfazl.nativeblur.NativeBlur
import java.lang.ref.WeakReference

class OverlayBlurView(parentActivity: Activity?, show: Boolean = false) {
    private lateinit var parentObject: WeakReference<Activity>
    private var blurredView: View? = null
    var clickListener: OnClickListener? = null

    var isShowing: Boolean = false
        private set

    init {
        parentActivity?.let {
            if (show) {
                checkParentActivity(parentActivity)
            } else {
                parentObject = WeakReference(parentActivity)
            }
        }
    }

    fun show() {
        if (isShowing) {
            return
        }

        if (blurredView != null) {
            blurredView?.visibility = View.VISIBLE
            isShowing = true
        } else {
            val parent = parentObject.get()

            if (parent is Activity) {
                getActivityRootView(parent)?.let {
                    prepareView(it)
                }
            }
        }
    }

    private fun getActivityRootView(activity: Activity?): View? {
        return activity?.window?.decorView?.findViewById(android.R.id.content)
    }

    private fun checkParentActivity(parentActivity: Activity) {
        getActivityRootView(parentActivity)?.let { view ->
            view.viewTreeObserver
                .addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        prepareView(view)
                    }
                })
        }
    }

    private fun prepareView(rootView: View) {
        if (isShowing || blurredView != null) {
            return
        }

        if (rootView is ViewGroup) {
            isShowing = true
            val width: Int = rootView.width
            val height: Int = rootView.height

            val w = (width / 5.0f).toInt()
            val h = (height / 5.0f).toInt()
            val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.scale(1.0f / 5.0f, 1.0f / 5.0f)
            rootView.draw(canvas)
            NativeBlur.blurBitmap(
                bitmap, 6.coerceAtLeast(w.coerceAtLeast(h) / 180), false
            )

            blurredView = View(rootView.context).also {
                it.background = BitmapDrawable(bitmap)
            }
            clickListener?.let {
                blurredView?.setOnClickListener(it)
            }

            rootView.removeView(blurredView)
            rootView.addView(
                blurredView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )
        } else {
            Logger.error("Root view should be a ViewGroup class.")
        }
    }

    fun hide() {
        if (isShowing) {
            isShowing = false
            blurredView?.visibility = View.GONE
        }
    }

    fun reset() {
        isShowing = false
        blurredView = null
    }
}
