plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.22"
    id("com.google.devtools.ksp") version "1.8.22-1.0.11"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.0.0-M4"
    id("com.adarshr.test-logger") version "3.2.0"
}

version = "0.1"
group = "com.example"

val kotlinVersion = project.properties["kotlinVersion"] as String
val coroutinesVersion = project.properties["coroutinesVersion"] as String

repositories {
    mavenCentral()
}

testlogger {
    theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
    showExceptions = true
    showFailed = true
    showPassed = true
    showSkipped = true
    showFailedStandardStreams = true
    showFullStackTraces = true
    slowThreshold = 30000L
}

dependencies {
    ksp("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:${coroutinesVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk9:${coroutinesVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${coroutinesVersion}")
    testImplementation("io.micronaut:micronaut-http-client")
}


application {
    mainClass.set("com.example.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

tasks {
    compileKotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    compileTestKotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
}

graalvmNative.toolchainDetection.set(false)

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
}
