package space.mori.kkutukotlin.authserver.database

import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.lang.Integer.parseInt

@Component
class Database {
    private var host: String = ""
    private var user: String = ""
    private var password: String = ""
    private var port: Int =3306
    private var database: String = ""

    init {
        val dotenv = dotenv()
        host = dotenv["DB_HOST"]
        user = dotenv["DB_USER"]
        password = dotenv["DB_PASSWORD"]
        port = parseInt(dotenv["DB_PORT"])
        database = dotenv["DB_DATABASE"]
    }

    fun connect() {
        // val databaseUrl = "jdbc:mariadb://$host:$port/$database"
        val databaseUrl = "jdbc:sqlite:../database.sqlite3"
        println("h$host u$user")
        Database.connect(url = databaseUrl )//, user = user, password = password, driver = "org.mariadb.jdbc.Driver")
        transaction {
            SchemaUtils.createMissingTablesAndColumns(UserTable)
        }
    }
}