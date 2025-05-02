package com.lucasalfare.urlshortner.main.a_domain

class Constants {
  companion object {
    const val H2_URL = "jdbc:h2:mem:regular"
    const val H2_DRIVER = "org.h2.Driver"

    const val DEFAULT_URL_ID_LENGTH = 8

    const val DEFAULT_WEBSERVER_PORT = 3000

    const val DEFAULT_URL_PREFIX = "http://localhost:$DEFAULT_WEBSERVER_PORT/"
  }
}