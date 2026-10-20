plugins {
    kotlin("jvm")
}

group = "com.emotionfactry.dreambook"
version = "0.0.0.1"

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}