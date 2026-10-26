package com.emotionfactry.dreambook.authservice.plugins

import com.emotionfactry.dreambook.authservice.model.Directions
import com.emotionfactry.dreambook.authservice.model.Email
import com.emotionfactry.dreambook.authservice.model.Password
import com.emotionfactry.dreambook.authservice.model.dto.LoginRequest
import com.emotionfactry.dreambook.authservice.model.RefreshToken
import com.emotionfactry.dreambook.authservice.model.UserId
import com.emotionfactry.dreambook.authservice.model.dto.DtoRefreshToken
import com.emotionfactry.dreambook.authservice.model.dto.RecordConsentRequest
import com.emotionfactry.dreambook.authservice.model.dto.RegisterRequest
import com.emotionfactry.dreambook.authservice.model.dto.UpdateProfileRequest
import com.emotionfactry.dreambook.authservice.services.AuthService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

fun Route.authRoutes(authService: AuthService) {
    route("/auth") {

        post("/register") {
            val request =  call.receive<RegisterRequest>()
            authService.register(
                email = Email(request.email),
                password = Password(request.password)
            )
            call.respond(HttpStatusCode.Created)
        }

        post("/login") {
            val request = call.receive<LoginRequest>()
            val tokens = authService.login(
                email = Email(request.email),
                password = Password(request.password)
            )
            call.respond(tokens)
        }

        post("/refresh") {
            val request = call.receive<DtoRefreshToken>()
            val tokens = authService.refresh(RefreshToken(request.refreshToken))
            call.respond(tokens)
        }

        authenticate("auth-jwt") {
            get("/profile") {
                val userId = call.userId()
                call.respond(authService.getProfile(userId))
            }
            put("/profile") {
                val userId = call.userId()
                val request = call.receive<UpdateProfileRequest>()
                authService.updateProfile(userId, request.preferredDirection ?: Directions.CLASSIC)
                call.respond(HttpStatusCode.NoContent)
            }
            post("/consents") {
                val userId = call.userId()
                val request = call.receive<RecordConsentRequest>()
                authService.recordConsent(userId, request.consentType, request.policyVersion)
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}