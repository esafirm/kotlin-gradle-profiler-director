@file:Suppress("UnstableApiUsage")

rootProject.name = "profilerdirector"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            library("kotlinx-serialization", "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
        }
    }
}

