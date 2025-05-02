package com.lucasalfare.urlshortner.main.c_infrastructure.webserver.ktor

import com.lucasalfare.flbase.configureRouting
import com.lucasalfare.flbase.configureSerialization
import com.lucasalfare.flbase.startWebServer
import com.lucasalfare.urlshortner.main.a_domain.Constants
import com.lucasalfare.urlshortner.main.a_domain.dto.ShortAnUrlRequestDTO
import com.lucasalfare.urlshortner.main.b_usecase.ShortUrlUsecase
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class KtorWebserver(private val usecase: ShortUrlUsecase) {

  fun start() {
    startWebServer(port = Constants.DEFAULT_WEBSERVER_PORT) {
      configureSerialization()

      configureRouting {
        // get("/") { /* HOME PAGE */ }

        post("/") {
          val shortAnUrlRequestDTO = call.receive<ShortAnUrlRequestDTO>()
          val result = usecase.shortOneUrl(shortAnUrlRequestDTO.longUrl)
          return@post call.respond(status = HttpStatusCode.OK, message = result.fullShortUrl())
        }

        get("/{shortUrl}") {
          val shortUrl =
            call.pathParameters["shortUrl"] ?: return@get call.respond(
              status = HttpStatusCode.BadRequest,
              message = "Short identifier not present."
            )

          val original = usecase.getOriginalUrl(shortUrl.split("/").last()) ?: return@get call.respond(
            status = HttpStatusCode.NoContent,
            message = "This short url doesn't maps to any long url."
          )

          return@get call.respondRedirect(url = original)
        }
      }
    }
  }
}

/*
curl.exe -X POST -H "Content-Type: application/json" -d '{\"longUrl\": \"https://www.google.com/search?q=generate+random+ascii+characters+kotlin&oq=generate+random+ascii+characters+kotlin&gs_lcrp=EgZjaHJvbWUyBggAEEUYOTIGCAEQLhhA0gEIOTgwM2owajGoAgCwAgA&sourceid=chrome&ie=UTF-8\"}' http://localhost:3000/url
 */