package space.mori.kkutukotlin.authserver.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HealthControler {
    @GetMapping("/health")
    fun home(): ResponseEntity<String> {
        return ResponseEntity.ok("")
    }
}