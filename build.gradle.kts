import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.41"
}

group = "pl.grzesiek"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compile("org.postgresql:postgresql:42.2.8")
    compile("org.mongodb:mongo-java-driver:3.11.1")
    compile("org.jooq:jooq:3.12.3")
    compile("com.zaxxer:HikariCP:3.4.1")
    compile ("ch.qos.logback:logback-core:1.2.3")
    compile ("ch.qos.logback:logback-classic:1.2.3")
    compile ("org.slf4j:slf4j-api:1.7.22")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
