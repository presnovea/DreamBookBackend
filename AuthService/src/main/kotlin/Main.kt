package com.emotionfactry.dreambook.authservice

import com.emotionfactry.dreambook.authservice.utils.createLogger
import com.emotionfactry.dreambook.authservice.utils.log

fun main() {
    createLogger()
    val name = "Auth service"
    val message = "Hello, " + name + "!"

    log.info(message)
}
