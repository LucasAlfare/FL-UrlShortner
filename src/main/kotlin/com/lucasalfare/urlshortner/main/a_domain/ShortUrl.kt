package com.lucasalfare.urlshortner.main.a_domain

data class ShortUrl(val id: String, val original: String) {
  fun fullShortUrl(prefix: String = Constants.DEFAULT_URL_PREFIX) = "$prefix$id"
}