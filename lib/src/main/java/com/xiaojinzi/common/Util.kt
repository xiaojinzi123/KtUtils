package com.xiaojinzi.common

import android.app.Application

internal var _app: Application? = null
val app: Application
get() = _app!!
