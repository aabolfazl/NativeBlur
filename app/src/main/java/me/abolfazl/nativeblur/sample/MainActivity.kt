package me.abolfazl.nativeblur.sample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import me.abolfazl.nativeblur.OverlayBlurView

class MainActivity : AppCompatActivity() {
    private lateinit var overlayBlurView: OverlayBlurView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overlayBlurView = OverlayBlurView(this)

        seekbarActivityButton.setOnClickListener {
            startActivity(Intent(this, SeekbarActivity::class.java))
        }

        staticBlur.setOnClickListener {
            startActivity(Intent(this, StaticActivity::class.java))
        }

        blurActivity.setOnClickListener {
            overlayBlurView.show()
            AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("This dialog just for test!")
                .setMessage("The background blurred :)")
                .setPositiveButton("Just close dialog") { dialog, _ -> dialog.dismiss() }
                .setNeutralButton("Close and Hide Blur") { _, _ -> overlayBlurView.hide() }
                .show()
        }

        blurActivity.setOnLongClickListener {
            overlayBlurView.reset()
            true
        }

        overlayBlurView.clickListener = View.OnClickListener { overlayBlurView.hide() }
    }

    override fun onBackPressed() {
        if (overlayBlurView.isShowing) {
            overlayBlurView.hide()
        } else {
            super.onBackPressed()
        }
    }
}