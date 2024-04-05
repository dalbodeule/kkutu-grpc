import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun
import java.util.*

plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.springframework.boot") version "2.6.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.graalvm.buildtools.native") version "0.10.1"
    kotlin("plugin.spring") version "1.9.23"
}

application {
    mainClass.set("${"${project.group}.${rootProject.name}.${project.name}".lowercase()}.MainKt")
}

java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.exposed:exposed-core:0.34.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.34.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.34.2")
    runtimeOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-oauth2-client")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.23.0")

    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

    implementation("me.paulschwarz:spring-dotenv:4.0.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-orgjson:0.12.5")

    // https://mvnrepository.com/artifact/org.springframework/org.springframework.web
    implementation("org.springframework:spring-web")

    // https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client
    implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")

    // https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc
    implementation("org.xerial:sqlite-jdbc:3.45.2.0")


    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
    val osName = System.getProperty("os.name").lowercase()
    val arch = System.getProperty("os.arch")

    val runningOnM1Mac = "mac" in osName && arch == "aarch64"
    if (runningOnM1Mac) {
        builder = "dashaun/builder:tiny"
        environment = mapOf("BP_NATIVE_IMAGE" to "true")
    }
}