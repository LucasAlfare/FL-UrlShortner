package com.lucasalfare.urlshortner.a_domain

class Constants {
  companion object {
    const val DEFAULT_IDENTIFIER_LENGTH = 6
    const val DEFAULT_WEBSERVER_PORT = 80
    val shortUrlPrefix = (System.getenv("URL_PREFIX") ?: "http://localhost:${DEFAULT_WEBSERVER_PORT}") + "/"
  }
}