package com.next.reservations.api

import com.next.reservations.web.request.User
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.*
import javax.servlet.http.HttpServletRequest

/*

@RestController
@RequestMapping("/login")
class UserController {

    fun login(@RequestBody user: User): Boolean {
        return user.username.equals("mefomefo") && user.password.equals("mefomzt")
    }

    @RequestMapping("/user")
    fun user(request: HttpServletRequest): Principal {
        val authToken = request.getHeader("Authorization")
                .substring("Basic".length).trim { it <= ' ' }
        return Principal {
            String(Base64.getDecoder()
                    .decode(authToken)).split(":").toTypedArray().get(0)
        }
    }
}
*/
