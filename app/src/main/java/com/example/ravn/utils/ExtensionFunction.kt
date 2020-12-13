package com.example.ravn.utils

import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.ravn.R

fun Fragment.disableSpinner() {
    activity?.runOnUiThread {
        activity?.findViewById<ConstraintLayout>(R.id.progress_bar)?.visibility =
                View.GONE
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}
fun Fragment.enableSpinner() {
    activity?.findViewById<ConstraintLayout>(R.id.progress_bar)?.visibility =
            View.VISIBLE
    activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}
fun AppCompatActivity.enableSpinner() {
    findViewById<ConstraintLayout>(R.id.progress_bar)?.visibility =
            View.VISIBLE
    window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )

}
