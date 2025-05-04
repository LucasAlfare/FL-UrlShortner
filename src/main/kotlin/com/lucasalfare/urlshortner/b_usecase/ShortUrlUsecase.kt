package com.lucasalfare.urlshortner.b_usecase

import com.lucasalfare.urlshortner.a_domain.IdentifierGenerator
import com.lucasalfare.urlshortner.a_domain.Repository
import com.lucasalfare.urlshortner.a_domain.ShortUrl
import com.lucasalfare.urlshortner.a_domain.dto.request.ShortUrlRequestDTO

class ShortUrlUsecase(private val repository: Repository) {
  suspend fun shortAnUrl(request: ShortUrlRequestDTO): ShortUrl =
    repository.getByOriginal(original = request.original).let {
      return@let if (it != null) it
      else {
        var nextIdentifier: String
        do {
          nextIdentifier = IdentifierGenerator.getRandomIdentifier()
        } while (repository.getByIdentifier(nextIdentifier) != null)

        repository.create(
          identifier = nextIdentifier,
          original = request.original
        )
      }
    }

  suspend fun getOriginalUrlFromShorten(shortenIdentifier: String): ShortUrl? =
    repository.getByIdentifier(shortenIdentifier)

  suspend fun getShortenIdentifierByOriginal(original: String): ShortUrl? =
    repository.getByOriginal(original)
}