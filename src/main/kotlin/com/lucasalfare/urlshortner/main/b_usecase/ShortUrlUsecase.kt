package com.lucasalfare.urlshortner.main.b_usecase

import com.lucasalfare.urlshortner.main.a_domain.Repository
import com.lucasalfare.urlshortner.main.a_domain.ShortUrl
import com.lucasalfare.urlshortner.main.a_domain.nextString

class ShortUrlUsecase(private val repository: Repository) {

  suspend fun shortOneUrl(longUrl: String): ShortUrl = repository.getByOriginal(longUrl).let {
    if (it != null) it
    else {
      var nextId: String
      do {
        nextId = nextString()
      } while (repository.getById(nextId) != null)
      repository.create(id = nextId, original = longUrl)
    }
  }

  suspend fun getOriginalUrl(shortUrlId: String): String? = repository.getById(shortUrlId)?.original
}