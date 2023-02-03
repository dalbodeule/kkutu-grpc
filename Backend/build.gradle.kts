plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.graalvm.buildtools.native") version "0.9.19"
}

val grpcVersion = "1.52.1"
val grpcKotlinVersion = "1.3.0"

val armeriaVersion = "1.21.0"

dependencies {
    implementation(project(":protobuf"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    setOf("armeria", "armeria-grpc", "armeria-bom", "armeria-logback").forEach {
        implementation("com.linecorp.armeria:${it}:${armeriaVersion}")
    }

    implementation("io.netty:netty-all:4.1.87.Final")

    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")

    runtimeOnly("org.slf4j:slf4j-simple:1.7.36")

    testImplementation("com.linecorp.armeria:armeria-junit5:${armeriaVersion}")
}

graalvmNative {
    agent {
        modes {
            direct {
                options.add("config-output-dir=\"../native_images\"")
            }
        }
    }
    binaries {
        named("main") {
            sharedLibrary.set(false)
            setOf(
                "--no-fallback",
                "--verbose",
                "--allow-incomplete-classpath",
                "--report-unsupported-elements-at-runtime",
                "--features=org.graalvm.home.HomeFinderFeature",

                "--initialize-at-build-time=${setOf(
                    "org.slf4j.impl.StaticLoggerBinder",
                    "org.slf4j.impl.JDK17LoggerAdapter",
                    "org.slf4j.LoggerFactory",
                    "org.slf4j.impl.SimpleLogger",
                    "com.aayushatharva.brotli4j.Brotli4jLoader"
                ).joinToString(",")}",
                "--initialize-at-run-time=${setOf(
                    "io.netty.buffer.UnpooledDirectByteBuf",
                    "io.netty.buffer.AbstractPooledDerivedByteBuf",
                    "io.netty.buffer.UnpooledHeapByteBuf",
                    "io.netty.handler.ssl.PemValue",
                    "io.netty.util.AbstractReferenceCounted",
                    "io.netty.handler.ssl.PemPrivateKey",
                    "io.netty.buffer.PooledByteBufAllocator",
                    "io.netty.buffer.UnpooledUnsafeDirectByteBuf",
                    "io.netty.buffer.ByteBufUtil",
                    "io.netty.buffer.UnpooledByteBufAllocator\$InstrumentedUnpooledUnsafeDirectByteBuf",
                    "io.netty.buffer.AbstractReferenceCountedByteBuf",
                    "io.netty.buffer.ByteBufAllocator",
                    "io.netty.handler.ssl.JdkNpnApplicationProtocolNegotiator",
                    "io.netty.handler.ssl.OpenSsl",
                    "io.netty.internal.tcnative.SSL",
                    "io.netty.handler.ssl.ReferenceCountedOpenSslEngine",
                    "io.netty.internal.tcnative.CertificateVerifier",
                    "io.netty.util.internal.logging.Log4JLogger",
                    "io.netty.handler.ssl.JettyNpnSslEngine",
                    "io.netty.handler.ssl.JettyAlpnSslEngine\$ClientEngine",
                    "io.netty.handler.ssl.JettyAlpnSslEngine\$ServerEngine",
                    "io.netty.handler.ssl.ConscryptAlpnSslEngine",
                    "io.netty.handler.ssl.BouncyCastleAlpnSslUtils",
                    "io.netty.handler.ssl.OpenSslPrivateKeyMethod",
                    "io.netty.internal.tcnative.AsyncSSLPrivateKeyMethod",
                    "io.netty.internal.tcnative.CertificateCompressionAlgo",
                    "io.netty.handler.ssl.OpenSslPrivateKeyMethod",
                    "io.netty.internal.tcnative.AsyncSSLPrivateKeyMethod",
                    "io.netty.internal.tcnative.CertificateCompressionAlgo",
                    "io.netty.handler.ssl.OpenSslPrivateKeyMethod",
                    "io.netty.internal.tcnative.AsyncSSLPrivateKeyMethod",
                    "io.netty.internal.tcnative.CertificateCompressionAlgo",
                    "io.netty.handler.ssl.OpenSslAsyncPrivateKeyMethod",
                    "io.netty.internal.tcnative.SSLPrivateKeyMethod",
                    "com.linecorp.armeria.common.ExceptionSampler",
                    "org.slf4j.impl.OutputChoice\$1"
                ).joinToString(",")}",
                "--trace-object-instantiation=${setOf(
                    "org.slf4j.impl.SimpleLogger"
                ).joinToString(",")}",
                "--trace-class-initialization=${setOf(
                    "io.netty.internal.tcnative.SSLPrivateKeyMethod"
                ).joinToString(",")}",
                "-H:-CheckToolchain",
                "-H:+ReportExceptionStackTraces",
                "-H:EnableURLProtocols=http,https"
            ).forEach {
                buildArgs.add(it)
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