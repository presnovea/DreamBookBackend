package com.emotionfactry.dreambook.authservice.utils

import java.lang.management.ManagementFactory
import org.slf4j.LoggerFactory
import org.slf4j.MDC

val log = LoggerFactory.getLogger("AuthService")
fun createLogger() {
    val pid = ManagementFactory.getRuntimeMXBean().name.split("@").first()
    MDC.put("serviceType", "AUTH-SERVICE") // Кладем имя сервиса по ключу "serviceType"
    MDC.put("pid", pid)                    // Кладем вычисленный PID по ключу "pid"
}

