plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}


dependencies {
    implementation("io.ktor:ktor-server-core-jvm:2.3.2")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.2")
    implementation("io.ktor:ktor-server-html-builder-jvm:2.3.2")
    implementation("io.ktor:ktor-server-thymeleaf:2.3.2")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("io.ktor:ktor-server-default-headers-jvm:2.3.2")
    implementation("io.ktor:ktor-server-call-logging-jvm:2.3.2")
    implementation("io.ktor:ktor-server-content-negotiation:2.0.0") // Para ContentType
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.2")
    testImplementation(kotlin("test"))
}


application {
    mainClass.set("com.example.ApplicationKt")
}