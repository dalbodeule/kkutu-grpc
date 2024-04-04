package space.mori.kkutukotlin.backend.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun main() {
    // Initialize the database connection
    Database.connect(
        url = "jdbc:mariadb://ubuntu.local:3306/kkutu",
        driver = "org.mariadb.jdbc.Driver",
        user = "test",
        password = "test"
    )

    // Create the users table
    transaction {
    }

}