package com.emotionfactry.dreambook.utils

/**
 * This is an extension function for logging.
 */
fun <T> T.dbLog(tag: String, message: String) {
    Log.i(tag, "${this!!::class.java.simpleName}, $message, ${Thread.currentThread().name} ")
}