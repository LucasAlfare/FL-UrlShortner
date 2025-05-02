package com.lucasalfare.urlshortner.main.c_infrastructure.database.exposed

import com.lucasalfare.flbase.database.AppDB
import com.lucasalfare.urlshortner.main.a_domain.Repository
import com.lucasalfare.urlshortner.main.a_domain.ShortUrl
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

object ExposedRepository : Repository {
  override suspend fun create(id: String, original: String): ShortUrl = AppDB.exposedQuery {
    try {
      Urls.insert {
        it[Urls.id] = id
        it[Urls.original] = original
      }
      ShortUrl(id, original)
    } catch (_: Exception) {
      throw Throwable("Error creating data!")
    }
  }

  override suspend fun getById(id: String): ShortUrl? = AppDB.exposedQuery {
    try {
      Urls.selectAll().where { Urls.id eq id }.singleOrNull()?.let {
        ShortUrl(
          id = it[Urls.id].value,
          original = it[Urls.original]
        )
      }
    } catch (_: Exception) {
      throw Throwable("error getting data!")
    }
  }

  override suspend fun getByOriginal(original: String): ShortUrl? = AppDB.exposedQuery {
    try {
      Urls.selectAll().where { Urls.original eq original }.singleOrNull()?.let {
        ShortUrl(
          id = it[Urls.id].value,
          original = it[Urls.original]
        )
      }
    } catch (_: Exception) {
      throw Throwable("error getting data!")
    }
  }
}