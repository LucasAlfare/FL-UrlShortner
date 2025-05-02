import com.lucasalfare.urlshortner.main.c_infrastructure.database.exposed.ExposedDatabase
import com.lucasalfare.urlshortner.main.c_infrastructure.database.exposed.ExposedRepository
import com.lucasalfare.urlshortner.main.c_infrastructure.database.exposed.Urls
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class TestExposedRepository {

  @BeforeTest
  fun setup() {
    ExposedDatabase.initialize(useH2Database = true)
  }

  @AfterTest
  fun dispose() {
    transaction { SchemaUtils.drop(Urls) }
  }

  @Test
  fun `test throwing on create data`() {
    assertDoesNotThrow {
      runBlocking {
        ExposedRepository.create("abcd", "AABBCCDD")
      }
    }

    assertThrows<Throwable> {
      runBlocking {
        ExposedRepository.create("abcd", "AABBCCDD")
      }
    }
  }
}