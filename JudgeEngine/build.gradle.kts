import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `cpp-application`
    cpp
	`visual-studio`
}

application {
    targetMachines.set(listOf(machines.linux.x86_64, machines.windows.x86_64))

    dependencies {
        // implementation(project(":protobuf:cpp"))
    }
}

val vcpkgIncludePath = "C:\\tools\\vcpkg\\installed\\x64-windows\\include"
val vcpkgLibPath = "C:\\tools\\vcpkg\\installed\\x64-windows\\lib"

tasks.withType<Jar> {
    enabled = false
}

tasks.withType<KotlinCompile> {
    enabled = false
}
tasks.withType<JavaCompile> {
    enabled = false
}

tasks.withType<CppCompile> {

    macros["NDEBUG"] = null

    compilerArgs.add("-W3")
    compilerArgs.add("-EHsc")
    compilerArgs.addAll(toolChain.map {
        when (it) {
            is Gcc, is Clang -> listOf("-O2", "-fno-access-control")
            is VisualCpp -> listOf("/Zi", "/I$vcpkgIncludePath")
            else -> listOf()
        }
    })
}

tasks.withType<LinkExecutable> {
    linkerArgs.addAll(toolChain.map {
        if (toolChain is VisualCpp) {
            return@map listOf("/LIBPATH:$vcpkgLibPath", "msapi.lib")
        } else {
            return@map listOf("")
        }
    })
}
