package com.zapp.app.application

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.zapp.app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    private lateinit var progressBar: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        progressBar = MaterialAlertDialogBuilder(this, R.style.Rounded_MaterialAlertDialog)
            .setCancelable(false)
            .setView(R.layout.loader_layout)
            .create()

    }

    fun showLoader() {
        progressBar.show()
        val window: Window? = progressBar.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(progressBar.window!!.attributes)
            layoutParams.width = 450
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.dimAmount = 0f
            progressBar.window!!.attributes = layoutParams
        }

    }

    fun hideLoader() {
        progressBar.dismiss()
    }

    override fun onDestroy() {
        progressBar.dismiss()
        super.onDestroy()
    }
}