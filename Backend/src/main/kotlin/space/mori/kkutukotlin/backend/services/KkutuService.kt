package space.mori.kkutukotlin.backend.services

import com.google.protobuf.Empty
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import space.mori.kkutukotlin.protobuf.ChatServiceGrpcKt
import space.mori.kkutukotlin.protobuf.kkutu
import java.util.concurrent.atomic.AtomicInteger


data class User(
    val uid: String,
    val username: String
)

class ChatRoom(val roomId: Int) {
    private val messages = MutableSharedFlow<kkutu.ChatResponse>()

    fun sendMessage(user: User, message: String) {
        runBlocking {
            messages.emit(kkutu.ChatResponse.newBuilder()
                .setUser(
                    kkutu.User.newBuilder()
                        .setUid("aaaa")
                        .setUsername("asef")
                        .build()
                )
                .setMsg(message)
                .build())
        }
    }

    fun receiveMessages(): Flow<kkutu.ChatResponse> {
        return messages.asSharedFlow()
    }
}

class KkutuService: ChatServiceGrpcKt.ChatServiceCoroutineImplBase() {
    private val roomIdCounter = AtomicInteger(1)
    private val chatRooms = mutableMapOf<Int, ChatRoom>()
    private val userRooms = mutableMapOf<User, ChatRoom>()

    init {
        chatRooms[0] = ChatRoom(0)
    }

    override fun chat(request: Empty): Flow<kkutu.ChatResponse> {
        val user = User("aaaa","asdf")
        val chatRoom = userRooms[user] ?: let {
            userRooms[user] = chatRooms[0]!!
            return@let userRooms[user]
        }
        return chatRoom?.receiveMessages() ?: emptyFlow()
    }

    override suspend fun sendMessage(request: kkutu.ChatRequest): Empty {
        val user = User(request.user.uid, request.user.username)
        val chatRoom = userRooms[user]
        chatRoom?.sendMessage(user, request.msg)
        return Empty.getDefaultInstance()
    }
}