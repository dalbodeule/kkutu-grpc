package space.mori.kkutukotlin.backend.services

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.protobuf.Empty
import com.linecorp.armeria.server.ServiceRequestContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import space.mori.kkutukotlin.backend.auth.JwtTokenInterceptor.Companion.USER_INFO_KEY
import space.mori.kkutukotlin.protobuf.ChatServiceGrpcKt
import space.mori.kkutukotlin.protobuf.kkutu
import java.util.concurrent.atomic.AtomicInteger

@JsonIgnoreProperties("token")
data class User(
    @JsonProperty("id") val id: String,
    @JsonProperty("nickname") val nickname: String,
    @JsonProperty("vendor") val vendor: String,
    @JsonProperty("profileImageUrl") val profile: String
) {
    constructor(userMap: Map<String, Any>): this (
        userMap["id"] as String,
        userMap["nickname"] as String,
        userMap["vendor"] as String,
        userMap["profileImageUrl"] as String,
    )
}

class ChatRoom(val roomId: Int) {
    private val users: MutableList<User> = mutableListOf()
    private val messages = MutableSharedFlow<kkutu.ChatResponse>()

    fun sendMessage(user: User, message: String) {
        runBlocking {
            val kuser = kkutu.User.newBuilder()
                .setUid(user.id)
                .setUsername(user.nickname)
                .build()
            messages.emit(kkutu.ChatResponse.newBuilder()
                .setUser(kuser)
                .setMsg(message)
                .build())
        }
    }

    fun receiveMessages(): Flow<kkutu.ChatResponse> {
        return messages.asSharedFlow()
    }

    fun addUser(user: User) {
        users.add(user)
    }

    fun removeUser(userId: String) {
        users.removeIf { it.id == userId }
    }

    fun getUsers(): List<User> {
        return users.toList()
    }
}

class KkutuService: ChatServiceGrpcKt.ChatServiceCoroutineImplBase() {
    private val roomIdCounter = AtomicInteger(1)
    private val chatRooms = mutableMapOf<Int, ChatRoom>()
    private val userRooms = mutableMapOf<User, ChatRoom>()
    private val objectMapper = ObjectMapper().registerModules()

    init {
        chatRooms[0] = ChatRoom(0)
    }

    override fun chat(request: kkutu.ChatIn): Flow<kkutu.ChatResponse> {
        val user = getUser()
        val roomId = request.roomId ?: 0
        var chatRoom = chatRooms[roomId]
        if(chatRoom == null) {
            chatRooms[roomId] = ChatRoom(roomId);
            chatRoom = chatRooms[roomId]!!
        }
        return chatRoom.receiveMessages().onStart {
            chatRoom.addUser(user)
        }.onCompletion {
            chatRoom.removeUser(user.id)
        }
    }

    override suspend fun sendMessage(request: kkutu.ChatRequest): Empty {
        val chatRoom = chatRooms[request.roomId ?: 0]
        val user = getUser()
        chatRoom?.sendMessage(user, request.msg)
        return Empty.getDefaultInstance()
    }

    private fun getUser(): User {
        val user: User = ServiceRequestContext.current().attr(USER_INFO_KEY)?.let {
            try {
                objectMapper.readValue(it, User::class.java)
            } catch(e: Exception) {
                throw e
            }
        } ?: User("", "Guest", "GUEST", "")

        return user
    }
}