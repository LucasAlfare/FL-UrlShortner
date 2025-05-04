package com.lucasalfare.urlshortner.c_infra.exposed

import com.lucasalfare.flbase.UnavailableDatabaseService
import com.lucasalfare.flbase.database.AppDB
import com.lucasalfare.urlshortner.a_domain.Repository
import com.lucasalfare.urlshortner.a_domain.ShortUrl
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

object ExposedRepository : Repository {
  override suspend fun create(
    identifier: String,
    original: String
  ): ShortUrl = AppDB.exposedQuery {
    try {
      Urls.insert {
        it[Urls.id] = identifier
        it[Urls.original] = original
      }
      ShortUrl(identifier, original)
    } catch (_: Exception) {
      throw UnavailableDatabaseService("Error inserting data in Exposed!")
    }
  }

  override suspend fun getByIdentifier(identifier: String): ShortUrl? = AppDB.exposedQuery {
    try {
      Urls.selectAll().where { Urls.id eq identifier }.singleOrNull().let {
        if (it == null) null
        else {
          ShortUrl(
            identifier = it[Urls.id].value,
            original = it[Urls.original]
          )
        }
      }
    } catch (_: Exception) {
      throw UnavailableDatabaseService("Error retrieving URL by identifier in exposed!")
    }
  }

  override suspend fun getByOriginal(original: String): ShortUrl? = AppDB.exposedQuery {
    try {
      Urls.selectAll().where { Urls.original eq original }.singleOrNull().let {
        if (it == null) null
        else {
          ShortUrl(
            identifier = it[Urls.id].value,
            original = it[Urls.original]
          )
        }
      }
    } catch (_: Exception) {
      throw UnavailableDatabaseService("Error retrieving URL by original in exposed!")
    }
  }
}