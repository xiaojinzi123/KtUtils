package com.xiaojinzi.common

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

// ============================== 基础的方法 ==============================

/**
 * 尝试从 Context 获取到 Activity
 */
fun Context.getActivity(): Activity? {
    val context = this
    var realActivity: Activity? = null
    if (context is Activity) {
        realActivity = context as Activity?
    } else {
        // 最终结束的条件是 realContext = null 或者 realContext 不是一个 ContextWrapper
        var realContext: Context? = context
        while (realContext is ContextWrapper) {
            realContext = realContext.baseContext
            if (realContext is Activity) {
                realActivity = realContext
                break
            }
        }
    }
    return realActivity
}

/**
 * 从 Context 尝试获取 FragmentActivity
 */
fun Context.getFragmentActivity(): FragmentActivity? {
    val targetAct = this.getActivity()
    return if (targetAct is FragmentActivity) {
        targetAct
    } else {
        null
    }
}

inline fun View.show() {
    this.visibility = View.VISIBLE
}

inline fun View.hide() {
    this.visibility = View.INVISIBLE
}

inline fun View.gone() {
    this.visibility = View.GONE
}

inline fun Int.dp(): Int {
    return (this.toFloat().dp() + 0.5f).toInt()
}

inline fun Float.dp(): Float {
    val dm = app.resources.displayMetrics
    return this * dm.density
}

inline fun Float.dpToInt(): Int {
    return (this.dp() + 0.5f).toInt()
}

// ============================== 获取系统资源方法 ==============================

inline fun Int.toResColor(): Int {
    return ContextCompat.getColor(app, this)
}

inline fun Int.toResString(): String {
    return app.getString(this)
}

inline fun Int.toResDrawable(): Drawable {
    return ContextCompat.getDrawable(app, this)!!
}