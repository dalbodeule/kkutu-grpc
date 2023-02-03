import com.google.protobuf.gradle.*

plugins {
    kotlin("jvm")
    application
    id("com.google.protobuf")
}

val grpcVersion = "1.52.1"
val grpcKotlinVersion = "1.3.0"
val grpcJavaVersion = "1.52.1"
val protocVersion = "3.21.12"
val protoKotlinVersion = "3.21.12"

dependencies {
    implementation("com.google.api.grpc:proto-google-common-protos")
    implementation("io.grpc:grpc-netty:$grpcVersion")

    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")

    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("com.google.protobuf:protobuf-kotlin:$protoKotlinVersion")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protocVersion}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcJavaVersion}"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:${grpcKotlinVersion}:jdk8@jar"
        }
    }

    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}

application {
    mainClass.set("${"${project.group}.${rootProject.name}.${project.name}".toLowerCase()}.MainKt")
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