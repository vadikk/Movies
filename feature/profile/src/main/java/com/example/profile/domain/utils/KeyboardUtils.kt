package com.example.profile.domain.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

internal fun hideKeyBoard(context: Context?, view: View?){
    if (view == null) return
    val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}