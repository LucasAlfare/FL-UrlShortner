package com.lucasalfare.urlshortner.a_domain

data class ShortUrl(
  val identifier: String,
  val original: String
) {

  fun fullShortUrl(prefix: String = Constants.shortUrlPrefix) = "$prefix$identifier"
}