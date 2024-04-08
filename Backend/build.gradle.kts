plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.graalvm.buildtools.native") version "0.9.10"
}

val grpcVersion = "1.61.1"
val grpcKotlinVersion = "1.4.1"

val armeriaVersion = "1.27.3"

dependencies {
    implementation(project(":protobuf"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")

    val exposedVersion: String = "0.49.0"
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    implementation("org.jetbrains.exposed:exposed-jodatime:$exposedVersion")
    // or
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    // or
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")

    implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-money:$exposedVersion")

    // https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client
    implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    
    setOf(
        "armeria",
        "armeria-kotlin",
        "armeria-grpc",
        "armeria-grpc-kotlin",
        "armeria-grpc-protocol",
        "armeria-bom",
        "armeria-logback14",
        "armeria-spring-boot3-starter"
    ).forEach {
        implementation("com.linecorp.armeria:${it}:${armeriaVersion}")
    }

    runtimeOnly("org.slf4j:slf4j-simple:1.7.36")

    testImplementation("com.linecorp.armeria:armeria-junit5:${armeriaVersion}")
}

sourceSets {
    main {
        kotlin {
            srcDir("src/main/kotlin")
        }
    }
}

graalvmNative {
    binaries {
        binaries.all {
            resources.autodetect()
        }
        named("main") {
            // Main options
            imageName = "backend" // The name of the native image, defaults to the project name
            useFatJar.set(true)
            sharedLibrary.set(false)
        }
    }
}

application {
    mainClass.set("${"${project.group}.${rootProject.name}.${project.name}".lowercase()}.MainKt")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}