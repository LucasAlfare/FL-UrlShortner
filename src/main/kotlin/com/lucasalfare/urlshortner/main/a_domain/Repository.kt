package com.lucasalfare.urlshortner.main.a_domain

interface Repository {

  suspend fun create(id: String, original: String): ShortUrl
  suspend fun getById(id: String): ShortUrl?
  suspend fun getByOriginal(original: String): ShortUrl?
}