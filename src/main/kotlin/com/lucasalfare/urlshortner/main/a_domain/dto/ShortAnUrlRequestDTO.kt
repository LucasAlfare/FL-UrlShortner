package com.lucasalfare.urlshortner.main.a_domain.dto

import kotlinx.serialization.Serializable

@Serializable
data class ShortAnUrlRequestDTO(
  val longUrl: String
)