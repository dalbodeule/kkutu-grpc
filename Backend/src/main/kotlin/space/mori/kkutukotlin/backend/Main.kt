package space.mori.kkutukotlin.backend

import com.linecorp.armeria.common.HttpHeaderNames
import com.linecorp.armeria.common.HttpMethod
import com.linecorp.armeria.common.grpc.GrpcSerializationFormats
import com.linecorp.armeria.common.grpc.protocol.GrpcHeaderNames
import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.cors.CorsService
import com.linecorp.armeria.server.grpc.GrpcService
import com.linecorp.armeria.server.logging.AccessLogWriter
import com.linecorp.armeria.server.logging.LoggingService
import io.grpc.protobuf.services.ProtoReflectionService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import space.mori.kkutukotlin.backend.services.KkutuService
import space.mori.kkutukotlin.backend.services.TestService

val logger: Logger = LoggerFactory.getLogger("Backend")

fun main() {
    val server = newServer()
    space.mori.kkutukotlin.backend.database.main()

    server.closeOnJvmShutdown()

    server.start().join()

    logger.info("Server has been started. http://${"127.0.0.1"}:${server.activeLocalPort()}/docs")
}

fun newServer(port: Int = 50000): Server {
    val sb = Server.builder()

    val grpcService = GrpcService.builder()
        .addService(TestService())
        .addService(KkutuService())
        .addService(ProtoReflectionService.newInstance())
        .supportedSerializationFormats(GrpcSerializationFormats.values())
        .enableUnframedRequests(true)
        .useBlockingTaskExecutor(true)
        .build()

    val corsSettings = CorsService.builder("http://127.0.0.1")
        .allowCredentials()
        .allowNullOrigin()
        .allowRequestMethods(HttpMethod.POST, HttpMethod.GET, HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE)
        .preflightResponseHeader("x-prelight-cors", "Hello CORS")
        .newDecorator()

    val grpcCorsSettings = CorsService.builder("http://127.0.0.1")
        .allowRequestMethods(HttpMethod.POST)
        .allowRequestHeaders(
            HttpHeaderNames.CONTENT_TYPE,
            HttpHeaderNames.of("X-GRPC-WEB")
        )
        .exposeHeaders(GrpcHeaderNames.GRPC_STATUS,
            GrpcHeaderNames.GRPC_MESSAGE,
            GrpcHeaderNames.ARMERIA_GRPC_THROWABLEPROTO_BIN
        )
        .newDecorator()

    return sb.http(port)
        .service(grpcService, grpcCorsSettings, LoggingService.newDecorator())
        .serviceUnder("/docs", BackendDocService.docService
            .decorate(corsSettings).decorate(LoggingService.newDecorator()))
        .accessLogWriter(AccessLogWriter.common(), true)
        .build()
}