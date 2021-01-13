package com.ssv.kyd.features.helper

import android.util.Log

class Logger {

    companion object {
        fun info(tag: String, msg: String) { Log.i(tag, msg) }
        fun debug(tag: String, msg: String) { Log.d(tag, msg) }
        fun verbose(tag: String, msg: String) { Log.v(tag, msg) }
        fun error(tag: String, msg: String) { Log.e(tag, msg) }
        fun warn(tag: String, msg: String) { Log.w(tag, msg) }
    }
}