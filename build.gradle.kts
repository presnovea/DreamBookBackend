plugins {
    alias(libs.plugins.kotlin.jvm)
}
group = "com.emotionfactry.dreambook"
version = "0.0.0.1"

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}