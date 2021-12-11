package me.abolfazl.nativeblur.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.abolfazl.nativeblur.NativeBlur

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NativeBlur.init(applicationContext)

    }
}