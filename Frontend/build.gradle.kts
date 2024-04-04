
import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.npm.task.NpxTask
import com.github.gradle.node.yarn.task.YarnTask

plugins {
    id("com.github.node-gradle.node") version "3.0.1"
}

val yarn = tasks.named("yarn")

configurations {
    compileOnly {
    }
}

node {
    version.set("19.5.0")
    nodeProjectDir.set(file("${projectDir}/src"))
}

tasks.withType<Jar> {
    enabled = false
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    enabled = false
}

tasks.withType<NpmTask> {
    enabled = false
}

tasks.register<YarnTask>("lint") {
    args.set(setOf("run", "lint"))
    dependsOn(tasks.yarnSetup)
    dependsOn(tasks.yarn)

    outputs.upToDateWhen { true }
}

tasks.clean.configure {
    actions.clear()

    listOf("nodejs", "npm", "yarn").onEach { delete("${projectDir}/.gradle/$it") }
    listOf("out", "build").onEach { delete("${projectDir}/$it") }
}

tasks.register<NpxTask>("runProtoc") {
    group = "build"
    description = "build protobuf with :protobuf projects"

    dependsOn(":protobuf:assemble")
    dependsOn(tasks.yarn)

    val targetFolder = "${projectDir}/src/assets/js/grpc"

    delete(targetFolder)
    mkdir(targetFolder)

    val isWindows = org.apache.tools.ant.taskdefs.condition.Os.isFamily(org.apache.tools.ant.taskdefs.condition.Os.FAMILY_WINDOWS)

    command.set("grpc_tools_node_protoc")

    setOf(
        "--plugin=\"protoc-gen-ts=${projectDir}/src/node_modules/.bin/protoc-gen-ts${if (isWindows) ".cmd" else ""}\"",
        "--grpc_out=\"grpc_js:$targetFolder\"",
        "--js_out=\"import_style=commonjs,binary:$targetFolder\"",
        "--ts_out=\"grpc_js:$targetFolder\"",
        "--proto_path",
        "${rootDir}/protobuf/src/main/proto/",
        "${rootDir}/protobuf/src/main/proto/*.proto"
    ).forEach {
        args.add(if (isWindows) it.replace("/", "\\") else it)
    }
}

tasks.register<YarnTask>("yarnRun") {
    yarnCommand.addAll(setOf("run", "dev"))
    dependsOn(tasks.yarnSetup)

    outputs.upToDateWhen { true }
}

tasks.register<YarnTask>("yarnBuild") {
    yarnCommand.addAll(setOf("run", "build"))
    dependsOn(tasks.yarnSetup)
    dependsOn("runProtoc")

    outputs.upToDateWhen { true }
}

tasks.run.configure {
    actions.clear()

    dependsOn("yarnRun")
}

tasks.build.configure {
    actions.clear()

    dependsOn("yarnBuild")

}