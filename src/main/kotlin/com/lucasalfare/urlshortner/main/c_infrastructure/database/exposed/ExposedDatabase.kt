package com.lucasalfare.urlshortner.main.c_infrastructure.database.exposed

import com.lucasalfare.flbase.Constants
import com.lucasalfare.flbase.database.AppDB
import org.jetbrains.exposed.sql.SchemaUtils

object ExposedDatabase {

  fun initialize(useH2Database: Boolean = false) {
    val url = if (useH2Database) com.lucasalfare.urlshortner.main.a_domain.Constants.H2_URL
    else Constants.SQLITE_URL

    val driver = if (useH2Database) com.lucasalfare.urlshortner.main.a_domain.Constants.H2_DRIVER
    else Constants.SQLITE_DRIVER

    AppDB.initialize(
      jdbcUrl = url,
      jdbcDriverClassName = driver,
      username = "",
      password = "",
      maximumPoolSize = 7
    ) {
      runCatching {
        SchemaUtils.create(Urls)
      }.onFailure {
        throw Throwable("Error when creating database tables.")
      }
    }
  }
}