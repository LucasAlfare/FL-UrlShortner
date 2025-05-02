package com.lucasalfare.urlshortner.main

import com.lucasalfare.urlshortner.main.b_usecase.ShortUrlUsecase
import com.lucasalfare.urlshortner.main.c_infrastructure.database.exposed.ExposedDatabase
import com.lucasalfare.urlshortner.main.c_infrastructure.database.exposed.ExposedRepository
import com.lucasalfare.urlshortner.main.c_infrastructure.webserver.ktor.KtorWebserver

fun main() {
  // defines the target data repository
  val dataRepository = ExposedRepository

  // create usecases handler
  val usecase = ShortUrlUsecase(dataRepository)

  // setup database
  val database = ExposedDatabase

  // setup webserver
  val ktorWebserver = KtorWebserver(usecase)

  // go!
  database.initialize()
  ktorWebserver.start()
}