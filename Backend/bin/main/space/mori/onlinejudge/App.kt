package space.mori.onlinejudge

import com.linecorp.armeria.server.Server
import mu.KotlinLogging

private val logger = kotlinLogging.logger { }

fun main() {
    val server = newServer()
    server.startServer()
}