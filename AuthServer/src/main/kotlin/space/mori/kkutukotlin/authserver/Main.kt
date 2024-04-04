package space.mori.kkutukotlin.authserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import space.mori.kkutukotlin.authserver.database.Database

@SpringBootApplication
@EnableScheduling
open class Application

fun main(args: Array<String>) {
    Database().connect()
    runApplication<Application>(*args)
}

