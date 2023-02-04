plugins {
    val kotlinVersion: String = "1.8.10"
    val protobufVersion: String = "0.9.2"

    kotlin("jvm") version kotlinVersion
    `java-library`
    id("com.google.protobuf") version protobufVersion
}

allprojects {
    repositories {
        mavenCentral()
    }

    group = "${project.group}"
    version = "${project.version}"

    val javaVersion = JavaVersion.VERSION_17.toString()

    tasks.withType<JavaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
        options.encoding = "UTF-8"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = javaVersion
        }
    }
}

val kotlinProjects = setOf("Backend", "Frontend")

subprojects {
    if (kotlinProjects.contains(project.name)) {
        apply(plugin = "kotlin")
        apply(plugin = "application")
        apply(plugin = "java-library")

        group = "${parent?.group}"
        version = "${parent?.version}"

        repositories {
            mavenCentral()
        }

        dependencies {
            implementation(kotlin("stdlib-jdk8"))
            implementation("org.apache.logging.log4j:log4j-core:2.18.0")
        }

        tasks.test {
            useJUnitPlatform()
        }
    }
}