import org.gradle.kotlin.dsl.support.kotlinCompilerOptions
import java.time.LocalDate

plugins {
    id("java-library")
    id("com.gradleup.shadow") version("8.3.3")
    id("maven-publish")
    id("org.cadixdev.licenser") version ("0.6.1")

}


group = "net.ofirtim.advancedchatmanagerplus"
version = figureVersion()

fun figureVersion(): String {
    return System.getenv("GITHUB_REF")?.toString() ?: "dev-SNAPSHOT"
}

allprojects {
    apply(plugin = "org.cadixdev.licenser")

    license {
        setHeader(project.rootProject.file("LICENSE.txt"))

        properties {
            properties["year"] = LocalDate.now().year.toString()
        }
    }

}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "com.gradleup.shadow")
    apply(plugin = "maven-publish")

    group = project.parent!!.group
    version = project.parent!!.version

    repositories {
        maven {
            name = "ApartiumNexus"
            url = uri("https://nexus.voigon.dev/repository/apartium")
        }

        maven {
            name = "AikarACF"
            url = uri("https://repo.aikar.co/content/groups/aikar/")
        }

    }

    dependencies {
        compileOnly("org.jetbrains:annotations:24.0.0")
        annotationProcessor("org.jetbrains:annotations:24.0.0")
    }

    val targetJavaVersion = 17
    java {
        val javaVersion = JavaVersion.toVersion(targetJavaVersion)
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
        if (JavaVersion.current() < javaVersion) {
            toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"

        if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
            options.release.set(targetJavaVersion)
        }
    }

    tasks.getByName("jar") {


       // setDestinationDirectory(File("$rootDir/output"))
    }
}
