package me.abolfazl.nativeblur.sample

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.abolfazl.nativeblur.NativeBlur

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.istanbul)
        imageView.setImageResource(R.drawable.istanbul)

        val blurBitmap = NativeBlur.blurBitmap(bitmap)
        blur_imageView.setImageBitmap(blurBitmap)

    }
}