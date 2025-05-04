package com.lucasalfare.urlshortner.c_infra.exposed

import com.lucasalfare.flbase.UnavailableDatabaseService
import com.lucasalfare.flbase.database.AppDB
import org.jetbrains.exposed.sql.SchemaUtils

object ExposedDatabase {

  fun initialize() {
    AppDB.initialize(
      jdbcUrl = System.getenv("DB_JDBC_URL") ?: "jdbc:sqlite:data.db",
      jdbcDriverClassName = System.getenv("DB_JDBC_DRIVER") ?: "org.sqlite.JDBC",
      username = System.getenv("DB_USERNAME") ?: "",
      password = System.getenv("DB_PASSWORD") ?: "",
      maximumPoolSize = 10
    ) {
      try {
        SchemaUtils.create(Urls)
      } catch (_: Exception) {
        throw UnavailableDatabaseService("Error creating data tables!")
      }
    }
  }
}