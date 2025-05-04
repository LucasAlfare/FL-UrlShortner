package com.lucasalfare.urlshortner.a_domain

import kotlin.random.Random

object IdentifierGenerator {

  fun getRandomIdentifier(
    length: Int = Constants.DEFAULT_IDENTIFIER_LENGTH,
    seed: Int = -1
  ): String {
    val r = if (seed == -1) Random else Random(seed)
    val availableCharacters = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (0..<length).map { availableCharacters.random(r) }.joinToString("")
  }
}