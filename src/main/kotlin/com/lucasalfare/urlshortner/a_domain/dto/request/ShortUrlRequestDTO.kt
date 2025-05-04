package com.lucasalfare.urlshortner.a_domain.dto.request

import com.lucasalfare.flbase.ValidationError
import kotlinx.serialization.Serializable

@Serializable
data class ShortUrlRequestDTO(
  val original: String
) {

  init {
    if (original.isEmpty()) {
      throw ValidationError("The [original] URL to short is empty.")
    }
  }
}