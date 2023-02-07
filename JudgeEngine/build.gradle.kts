
import com.github.tomtzook.gcmake.tasks.CmakeBuildTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.ByteArrayOutputStream

plugins {
    id("io.github.tomtzook.gradle-cmake") version "1.2.2"
}

fun runCommands(command: String): String {
    val byteOut = ByteArrayOutputStream()

    project.exec {
        commandLine = command.split(" ")
        standardOutput = byteOut
    }

    return byteOut.toString().trim()
}

machines.customMachines.register("linux-x86_64") {
    this.toolchainFile.set(project.file("x86_64-linux-gnu-gcc.cmake"))
}

cmake {
    targets.create("JudgeEngine") {
        cmakeLists.set(file("${projectDir}/CMakeLists.txt"))
        targetMachines.add(machines.host)
    }
}

tasks.withType<CmakeBuildTask> {
    dependsOn("copyProto")

    args.add("-j 10")
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

tasks.register("clean") {
    group = "build"
    description = "Clean build folders"

    dependsOn("cmakeClean")
}

tasks.register("copyProto") {
    group = "build"
    description = "Copy .proto files from :protobuf subprojects"

    dependsOn(":protobuf:build")

    copy {
        from(fileTree("${rootDir}/protobuf/src/main/proto").include("**/*.proto"))
        into("${projectDir}/proto/myproto")
    }
}

tasks.register("build") {
    group = "build"
    description = "Build this projects"

    dependsOn("cmakeBuild")
}