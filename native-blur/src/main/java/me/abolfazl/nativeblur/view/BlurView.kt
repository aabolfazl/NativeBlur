/*
*
* Copyright (c) 2021 Abolfazl Abbasi
*
* */

package me.abolfazl.nativeblur.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import me.abolfazl.nativeblur.NativeBlur
import me.abolfazl.nativeblur.R

class BlurView : AppCompatImageView {
    private var compress: Boolean = true
    private var lastRadius: Int = 0
    private var renderingBitmap: Bitmap? = null
    private var originalBitmap: Bitmap? = null

    var radius: Int = 14
        set(value) {
            field = value
            setBlurIfChanged()
        }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        fillAttrs(attrs)
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        fillAttrs(attrs)
        init()
    }

    private fun fillAttrs(attrs: AttributeSet) {
        val array = context.obtainStyledAttributes(attrs, R.styleable.BlurView)

        radius = array.getInt(R.styleable.BlurView_radius, radius)
        compress = array.getBoolean(R.styleable.BlurView_compress, compress)

        array.recycle()
    }

    private fun init() {
        if (drawable != null && drawable is BitmapDrawable) {
            originalBitmap = (drawable as BitmapDrawable).bitmap
        }

        setBlurIfChanged()
    }

    private fun setBlurIfChanged() {
        if (lastRadius == radius) {
            return
        }

        originalBitmap?.let { bitmap ->
            if (radius < 1) {
                setImageDrawable(BitmapDrawable(bitmap))
            } else {
                if (renderingBitmap == null) {
                    renderingBitmap = bitmap
                }

                val blurBitmap = NativeBlur.blurBitmap(renderingBitmap!!, radius, compress)
                setImageDrawable(BitmapDrawable(blurBitmap))
            }
        }

        lastRadius = radius
    }

}