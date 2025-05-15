package com.epsports.platzifakestore

import android.widget.EditText

object Extension {
    fun EditText.value(): String{
        return this.text.toString().trim()
    }
}