import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "learning"
version = "1.0-SNAPSHOT"

plugins {
    val kotlinVersion = "1.3.11"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("io.quarkus") version "0.14.0"
 }

repositories {
    jcenter()
    maven {
        if (System.getenv("CDP_BUILD_VERSION") == null) {
            val mavenUser: String by project
            val mavenPassword: String by project
            credentials {
                username = mavenUser
                password = mavenPassword
            }
        }
        url = uri("https://maven.zalando.net/content/repositories/releases")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.quarkus:quarkus-resteasy:0.14.0")
    implementation("io.quarkus:quarkus-kotlin:0.14.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testCompile("io.quarkus:quarkus-junit5:0.14.0")
    testCompile("io.rest-assured:rest-assured:3.3.0")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
//all-open is this still needed?
tasks.test {
    useJUnitPlatform()
}

quarkus {
    setSourceDir("src/main/kotlin")
}