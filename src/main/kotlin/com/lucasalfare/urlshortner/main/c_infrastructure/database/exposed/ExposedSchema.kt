package com.lucasalfare.urlshortner.main.c_infrastructure.database.exposed

import com.lucasalfare.urlshortner.main.a_domain.Constants
import org.jetbrains.exposed.dao.id.IdTable

object Urls : IdTable<String>(name = "Urls") {
  val original = text(name = "original")
  override val id = varchar(name = "identifier", length = Constants.DEFAULT_URL_ID_LENGTH).entityId().uniqueIndex()
  override val primaryKey = PrimaryKey(firstColumn = id)
}