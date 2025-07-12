import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "1.9.10"
    id("org.jetbrains.compose") version "1.5.10"
}

group = "net.noncore.reportify"
version = "1.0.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.ui)
}

compose.desktop {
    application {
        mainClass = "net.noncore.reportify.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Reportify"
            packageVersion = "1.0.0"
            windows {
                menuGroup = "Reportify"
                upgradeUuid = "89f599e6-3d33-4b3c-8c21-8c87b8b8b8b8"
            }
            macOS {
                bundleID = "net.noncore.reportify"
                appCategory = "public.app-category.productivity"
            }
            linux {
                appCategory = "Utility"
            }
        }
    }
}

kotlin {
    jvmToolchain(17)
}