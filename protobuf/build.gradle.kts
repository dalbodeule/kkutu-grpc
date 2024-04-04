import com.google.protobuf.gradle.id

val javaVersion = JavaVersion.VERSION_17.toString()

tasks.withType<JavaCompile> {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    options.encoding = "UTF-8"
}

plugins {
    id("com.google.protobuf")
    java
    kotlin("jvm")
}

dependencies {
    implementation("com.google.api.grpc:proto-google-common-protos:2.33.0")

    implementation(kotlin("stdlib-jdk8"))

    implementation("io.grpc:grpc-stub:1.61.1")
    implementation("io.grpc:grpc-protobuf:1.61.1")
    implementation("io.grpc:grpc-kotlin-stub:1.4.1")

    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
}

sourceSets {
    main {
        proto {
            srcDir("src/main/proto")
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    dependsOn("generateProto")
}

tasks.withType<JavaCompile> {
    dependsOn("generateProto")
}

tasks.withType<ProcessResources> {
    enabled = false
}

val protocVersion = "3.25.2"

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protocVersion}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.61.1"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.1:jdk8@jar"
        }
    }

    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc") {
                    outputSubDir = "java"
                }
                id("grpckt") {
                    outputSubDir = "kotlin"
                }
            }
        }

        ofSourceSet("main")
    }
}

tasks.clean.configure {
    delete(setOf(
        "java",
        "kotlin"
    ).map { "${protobuf.generatedFilesBaseDir}/${it}" })
}

tasks.withType<Jar> {
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}