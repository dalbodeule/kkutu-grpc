package space.mori.kkutukotlin.backend.services

import com.linecorp.armeria.server.ServiceRequestContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import space.mori.kkutukotlin.protobuf.Test
import space.mori.kkutukotlin.protobuf.TestServiceGrpcKt
import java.util.concurrent.atomic.AtomicInteger

class TestService: TestServiceGrpcKt.TestServiceCoroutineImplBase() {
    override suspend fun sayHello(request: Test.HelloRequest): Test.HelloResponse {
        request.greeting

        ServiceRequestContext.current()
        return Test.HelloResponse.newBuilder().setGreeting("Hello ${request.greeting ?: "UNKNOWN"}!").build()
    }

    companion object {
        fun armeriaBlockingDispatcher(): CoroutineDispatcher =
            ServiceRequestContext.current().blockingTaskExecutor().asCoroutineDispatcher()

        suspend fun <T> withArmeriaBlockingContext(block: suspend CoroutineScope.() -> T): T =
            withContext(ServiceRequestContext.current().blockingTaskExecutor().asCoroutineDispatcher(), block)
    }
}