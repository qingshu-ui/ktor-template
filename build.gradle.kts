plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}

group = libs.versions.group.get()
version = libs.versions.version.get()

application {
    mainClass = libs.versions.mainClass.get()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.sessions)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.core)
    implementation(libs.htmlDsl)
    implementation(libs.cssDsl)
    implementation(libs.exposedCore)
    implementation(libs.exposedJdbc)
    implementation(libs.exposedDao)
    implementation(libs.exposedDateTime)
    implementation(libs.sqlLite)
    implementation(libs.kotlinx.serializationJson)
    implementation(libs.flexmarkAll)
    implementation(libs.koinCore)
    implementation(libs.koinLogger)
    implementation(libs.koinKtor)
    implementation(libs.bcrypt)
    testImplementation(libs.koinTest)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}