package com.lucasalfare.urlshortner.c_infra.exposed

import com.lucasalfare.urlshortner.a_domain.Constants
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object Urls : IdTable<String>("") {
  override val id: Column<EntityID<String>> =
    varchar(
      name = "identifier",
      length = Constants.DEFAULT_IDENTIFIER_LENGTH
    ).entityId().uniqueIndex()

  val original = text("original")
}