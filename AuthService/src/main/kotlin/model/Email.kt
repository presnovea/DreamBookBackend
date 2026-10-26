package com.emotionfactry.dreambook.authservice.model

@JvmInline
value class Email(val email: String) {
    init {
        require(email.isNotEmpty()) { "Email cannot be empty" }
        require(email.contains("@")) { "Email must contain '@'" }
    }
}