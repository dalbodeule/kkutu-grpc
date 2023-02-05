import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.ByteArrayOutputStream

plugins {
    `cpp-application`
    cpp
}

fun runCommands(command: String): String {
    val byteOut = ByteArrayOutputStream()

    project.exec {
        commandLine = command.split(" ")
        standardOutput = byteOut
    }

    return byteOut.toString().trim()
}

fun getPath(name: String): String {
    val commands = "which $name"

    val path = runCommands(commands)

    if (path.isEmpty()) {
        println("Failed to locate path for $name")
    } else {
        println("Found path for $name at $commands")
    }

    return path
}

application {
    targetMachines.set(listOf(machines.linux.x86_64))

    dependencies {
    }
}

tasks.withType<Jar> {
    enabled = false
}

tasks.withType<KotlinCompile> {
    enabled = false
}
tasks.withType<JavaCompile> {
    enabled = false
}

task("runProtoc") {
    group = "build"
    description = "build protobuf with :protobuf projects"

    dependsOn(":protobuf:assemble")

    runCommands("protoc -I ${rootProject.rootDir}/protobuf/src/main/proto --cpp_out=${project.projectDir}/src/main/headers ${rootProject.rootDir}/protobuf/src/main/proto/*.proto")
}

tasks.withType<CppCompile> {
    dependsOn("runProtoc")

    macros["NDEBUG"] = null

    val includePath = "${rootProject.property("vcpkg.includePath")}"

    compilerArgs.add("-W3")
    compilerArgs.add("-EHsc")
    compilerArgs.addAll(toolChain.map {
        return@map when (it) {
            is Gcc, is Clang -> listOf("-O2", "-fno-access-control")
            is VisualCpp -> throw Error("This project required GCC or CLang")
            else -> listOf()
        }
    })
}

tasks.withType<LinkExecutable> {
    val libPath = "${rootProject.property("vcpkg.libPath")}"

    println(libPath)

    linkerArgs.addAll(toolChain.map {
        return@map if (it is VisualCpp) {
            throw Error("This project required GCC or CLang")
        } else {
            listOf("")
        }
    })
}
