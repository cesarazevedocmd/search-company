package com.cesar.ioasysempresasandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun <T> Context.goTo(activity: Class<T>, bundle: Bundle = Bundle()) {
    val intent = Intent(this, activity).apply { putExtras(bundle) }
    this.startActivity(intent)
}

fun Context.color(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}