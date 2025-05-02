package com.lucasalfare.urlshortner.main.a_domain

import kotlin.random.Random

fun nextString(
  length: Int = Constants.DEFAULT_URL_ID_LENGTH,
  seed: Int = -1
): String {
  val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
  val nextRandomness = if (seed >= 0) Random(seed) else Random
  return (1..length)
    .map { allowedChars.random(random = nextRandomness) }
    .joinToString("")
}