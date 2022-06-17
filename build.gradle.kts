plugins {
    kotlin("multiplatform") version "1.7.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.10"
    application
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("com.github.ajalt.clikt:clikt:3.5.0")
                implementation(libs.kotlinx.serialization)
            }
        }
        named("jvmMain")
    }
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("nolambda.stream.profilerdirector.MainKt")
}
