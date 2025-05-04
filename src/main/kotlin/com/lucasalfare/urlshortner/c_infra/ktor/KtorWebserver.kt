package com.lucasalfare.urlshortner.c_infra.ktor

import com.lucasalfare.flbase.*
import com.lucasalfare.urlshortner.a_domain.Constants
import com.lucasalfare.urlshortner.a_domain.dto.request.ShortUrlRequestDTO
import com.lucasalfare.urlshortner.b_usecase.ShortUrlUsecase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class KtorWebserver(private val usecase: ShortUrlUsecase) {

  fun start() {
    startWebServer(port = Constants.DEFAULT_WEBSERVER_PORT) {
      configureSerialization()
      configureCORS()
      configureStatusPages()
      configureStaticHtml(Pair("/", "index.html"))

      configureRouting {
        get("/{shortenUrl}") {
          val identifier = call.pathParameters["shortenUrl"] ?: throw BadRequest("Bad identifier in short url!")
          val originalUrl =
            usecase.getOriginalUrlFromShorten(identifier) ?: throw AppError("Identifier doesn't exists in database!")
          return@get call.respondRedirect(originalUrl.original)
        }

        post("/short") {
          runCatching {
            call.receive<ShortUrlRequestDTO>()
          }.onSuccess { request ->
            val result = usecase.shortAnUrl(request)
            return@post call.respond(HttpStatusCode.Created, result.fullShortUrl())
          }.onFailure {
            throw SerializationError("Error deserializing request data!")
          }
        }
      }
    }
  }
}