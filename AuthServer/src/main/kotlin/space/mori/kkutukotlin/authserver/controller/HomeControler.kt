package space.mori.kkutukotlin.authserver.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeControler {
    @GetMapping("/")
    fun home(): ResponseEntity<String> {
        return ResponseEntity.ok("")
    }
}