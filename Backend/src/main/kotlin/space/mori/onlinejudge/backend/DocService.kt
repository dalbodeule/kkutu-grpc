package space.mori.onlinejudge.backend

import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.docs.DocServiceFilter
import io.grpc.reflection.v1alpha.ServerReflectionGrpc
import space.mori.onlinejudge.protobuf.TestServiceGrpcKt

object BackendDocService  {
    private val service = DocService.builder()

    private val docList = setOf<DefineRequest>(
        DefineRequest(TestServiceGrpcKt.SERVICE_NAME, "sayHello", DocServiceFilter.ofServiceName(ServerReflectionGrpc.SERVICE_NAME))
    )

    init {
        loadServices()
    }

    private fun loadServices(): Boolean {
        docList.forEach {
            it.docServiceFilter?.let { it1 ->
                service.exampleRequests(
                    it.serviceName, it.methodName
                ).exclude(
                    it1
                )
            } ?: {
                service.exampleRequests(
                    it.serviceName, it.methodName
                )
            }
        }

        return true
    }

    val docService: DocService
        get() = service.build()
}

data class DefineRequest(val serviceName: String, val methodName: String, val docServiceFilter: DocServiceFilter?)