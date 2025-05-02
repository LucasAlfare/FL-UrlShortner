import com.lucasalfare.urlshortner.main.c_infrastructure.database.exposed.ExposedDatabase
import com.lucasalfare.urlshortner.main.c_infrastructure.database.exposed.Urls
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class TestShortUrlUsecase {

  @BeforeTest
  fun setup() {
    ExposedDatabase.initialize(useH2Database = true)
  }

  @AfterTest
  fun dispose() {
    transaction { SchemaUtils.drop(Urls) }
  }

  @Test
  fun `test shortOneUrl()`() {
    // TODO
  }
}