
import com.github.gradle.node.npm.task.NpmTask
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

    outputs.upToDateWhen { true }
}

tasks.clean.configure {
    actions.clear()

    listOf("nodejs", "npm", "yarn").onEach { delete("${projectDir}/.gradle/$it") }
    listOf("out", "build").onEach { delete("${projectDir}/$it") }
}

tasks.register<YarnTask>("yarnRun") {
    yarnCommand.addAll(setOf("run", "dev"))
    dependsOn(tasks.yarnSetup)

    outputs.upToDateWhen { true }
}

tasks.register<YarnTask>("yarnBuild") {
    yarnCommand.addAll(setOf("run", "build"))
    dependsOn(tasks.yarnSetup)

    outputs.upToDateWhen { true }
}

tasks.run.configure {
    actions.clear()

    dependsOn("lint")
    dependsOn("yarnRun")
}

tasks.build.configure {
    actions.clear()

    dependsOn("lint")
    dependsOn("yarnBuild")

}