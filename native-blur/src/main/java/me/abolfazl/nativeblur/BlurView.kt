package me.abolfazl.nativeblur

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class BlurView : AppCompatImageView {
    private var compress: Boolean = true
    private var lastRadius: Int = 0
    private var renderingBitmap: Bitmap? = null
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
            renderingBitmap = (drawable as BitmapDrawable).bitmap
        }

        setBlurIfChanged()
    }

    private fun setBlurIfChanged() {
        if (lastRadius == radius) {
            return
        }

        renderingBitmap?.let { bitmap ->
            val blurBitmap = NativeBlur.blurBitmap(bitmap, radius, compress)
            setImageDrawable(BitmapDrawable(blurBitmap))
            lastRadius = radius
        }
    }

}