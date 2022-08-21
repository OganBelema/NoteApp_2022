package com.oganbelema.hellocomposev

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDate(): String {
    val format = SimpleDateFormat("EEE, d MMM hh:mm aaa", Locale.getDefault())
    return format.format(this)

}