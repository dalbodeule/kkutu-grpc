package space.mori.kkutukotlin.authserver.database

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

object UserTable: UUIDTable("users") {
    val vendor = varchar("vendor", 20)
    val token = varchar("token", 255)
    val nickname = varchar("nickname", 20)
    val profileImage = varchar("profileImage", 255)
}

class UserEntity(uid: EntityID<UUID>): UUIDEntity(uid) {
    companion object: UUIDEntityClass<UserEntity>(UserTable)
    var vendor by UserTable.vendor
    var token by UserTable.token
    var nickname by UserTable.nickname
    var profileImage by UserTable.profileImage
}

fun findUser(token: String, vendor: String): UserEntity? {
    return transaction {
        return@transaction UserEntity.find {
            UserTable.token.eq(token) and UserTable.vendor.eq(vendor)
        }.firstOrNull()
    }
}

fun createUser(token: String, vendor: String, nickname: String, profileImage: String) {
    transaction {
        UserEntity.new {
            this.token = token.toString()
            this.vendor = vendor.toString()
            this.nickname = nickname.toString()
            this.profileImage = profileImage.toString()
        }
    }
}