package com.lucasalfare.urlshortner.a_domain

interface Repository {

  suspend fun create(identifier: String, original: String): ShortUrl
  suspend fun getByIdentifier(identifier: String): ShortUrl?
  suspend fun getByOriginal(original: String): ShortUrl?
}