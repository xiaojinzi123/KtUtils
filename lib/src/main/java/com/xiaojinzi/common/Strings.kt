package com.xiaojinzi.common

/**
 * 是否都是数字
 */
fun String.isAllDigit() = this.all { charItem -> Character.isDigit(charItem) }