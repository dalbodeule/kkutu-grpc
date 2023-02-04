import com.google.protobuf.gradle.id
import java.io.ByteArrayOutputStream

fun getPluginPath(name: String): String {
    val winCommands = "C:\\tools\\vcpkg\\installed\\x64-windows\\tools\\grpc\\grpc_cpp_plugin.exe"
    val linCommands = "which grpc_${name}_plugin"

    val isWindows = org.apache.tools.ant.taskdefs.condition.Os.isFamily(org.apache.tools.ant.taskdefs.condition.Os.FAMILY_WINDOWS)

    val commands = if (isWindows) {
        println("Found GRPC plugin for $name at $winCommands")
        return winCommands
    } else linCommands

    val byteOut = ByteArrayOutputStream()

    project.exec {
        commandLine = commands.split(" ")
        standardOutput = byteOut
    }

    val path = byteOut.toString().trim()

    if (path.isEmpty()) {
        println("Failed to locate GRPC plugin for $name")
    } else {
        println("Found GRPC plugin for $name at $winCommands")
    }

    return path
}

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
    implementation("com.google.api.grpc:proto-google-common-protos:2.14.0")

    implementation(kotlin("stdlib-jdk8"))

    implementation("io.grpc:grpc-stub:1.52.1")
    implementation("io.grpc:grpc-protobuf:1.52.1")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")

    implementation("javax.annotation:javax.annotation-api:1.3.2")
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

val protocVersion = "3.21.12"

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protocVersion}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.52.1"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
        id("grpc_cpp") {
            path = getPluginPath("cpp")
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
                id("grpc_cpp") {
                    outputSubDir = "cpp"
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