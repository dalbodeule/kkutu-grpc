package space.mori.onlinejudge.backend.services

import com.linecorp.armeria.server.ServiceRequestContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.withContext
import space.mori.onlinejudge.proto.Test
import space.mori.onlinejudge.proto.TestServiceGrpcKt

class TestService: TestServiceGrpcKt.TestServiceCoroutineImplBase() {
    override suspend fun sayHello(request: Test.HelloRequest): Test.HelloResponse {
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