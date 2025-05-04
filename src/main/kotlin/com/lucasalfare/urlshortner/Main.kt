package com.lucasalfare.urlshortner

import com.lucasalfare.urlshortner.b_usecase.ShortUrlUsecase
import com.lucasalfare.urlshortner.c_infra.exposed.ExposedDatabase
import com.lucasalfare.urlshortner.c_infra.exposed.ExposedRepository
import com.lucasalfare.urlshortner.c_infra.ktor.KtorWebserver

fun main() {
  val usecase = ShortUrlUsecase(ExposedRepository)
  val ktorWebserver = KtorWebserver(usecase)
  ExposedDatabase.initialize()
  ktorWebserver.start()
}