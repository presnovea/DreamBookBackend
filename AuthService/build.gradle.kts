import org.gradle.kotlin.dsl.testImplementation

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    application
}

group = "com.emotionfactry.dreambook"
version = "0.0.0.1"

application {
    mainClass.set("com.emotionfactry.dreambook.authservice.MainKt")
}

dependencies {
    testImplementation(kotlin("test"))

    implementation(libs.at.favre.bcrypt)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.logback.classic)

    testImplementation(libs.ktor.server.test.host)
}