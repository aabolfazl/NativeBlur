package me.abolfazl.nativeblur.sample.activty

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_static.*
import me.abolfazl.nativeblur.NativeBlur
import me.abolfazl.nativeblur.sample.R

class StaticActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static)

        val source = BitmapFactory.decodeResource(resources, R.drawable.spacex2)
        val bitmap = NativeBlur.blurBitmap(source)
        staticImageView.setImageBitmap(bitmap)

    }
}