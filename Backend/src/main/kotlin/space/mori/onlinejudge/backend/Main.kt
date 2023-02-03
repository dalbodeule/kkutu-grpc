package space.mori.onlinejudge.backend

import com.linecorp.armeria.common.grpc.GrpcSerializationFormats
import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.docs.DocServiceFilter
import com.linecorp.armeria.server.grpc.GrpcService
import io.grpc.protobuf.services.ProtoReflectionService
import io.grpc.reflection.v1alpha.ServerReflectionGrpc
import org.slf4j.LoggerFactory
import space.mori.onlinejudge.backend.services.TestService
import space.mori.onlinejudge.proto.TestServiceGrpc

private val logger = LoggerFactory.getLogger("Backend")

fun main(args: Array<String>) {
    val server = newServer()

    server.closeOnJvmShutdown()

    server.start().join()

    logger.info("Server has been started. http://127.0.0.1:${server.activeLocalPort()}/docs")
}

fun newServer(port: Int = 8080): Server {
    val sb = Server.builder()
    val docService = DocService.builder()
        .exampleRequests(
            TestServiceGrpc.SERVICE_NAME,
            "sayHello",
            "asdf"
        ).exclude(
            DocServiceFilter.ofServiceName(
                ServerReflectionGrpc.SERVICE_NAME
            )
        )
        .build()

    val grpcService = GrpcService.builder()
        .addService(TestService())
        .addService(ProtoReflectionService.newInstance())
        .supportedSerializationFormats(GrpcSerializationFormats.values())
        .enableUnframedRequests(true)
        .useBlockingTaskExecutor(true)
        .build()

    return sb.http(port)
        .service(grpcService)
        .serviceUnder("/docs", docService)
        .build()
}