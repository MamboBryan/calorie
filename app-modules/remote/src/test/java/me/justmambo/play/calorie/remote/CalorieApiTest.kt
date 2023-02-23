package me.justmambo.play.calorie.remote

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.justmambo.play.calorie.remote.models.ItemsDto
import org.junit.Test
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.assertTrue

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Thu 23 Feb 2023
 */
class CalorieApiTest {

    companion object {

        val VALID_RESPONSE = """
            {
            	"items": [
            		{
            			"name": "rice",
            			"calories": 127.4,
            			"serving_size_g": 100.0,
            			"fat_total_g": 0.3,
            			"fat_saturated_g": 0.1,
            			"protein_g": 2.7,
            			"sodium_mg": 1,
            			"potassium_mg": 42,
            			"cholesterol_mg": 0,
            			"carbohydrates_total_g": 28.4,
            			"fiber_g": 0.4,
            			"sugar_g": 0.1
            		}
            	]
            }
        """.trimIndent()

        val INVALID_RESPONSE = """
            {
            	"items": [
            		{
            			"name": "rice",
            			"calories": 127.4,
            			"serving_size_g": 100.0,
            			"fat_saturated_g": 0.1,
            			"protein_g": 2.7,
            			"sodium_mg": 1,
            			"potassium_mg": 42,
            			"cholesterol_mg": 0,
            			"carbohydrates_total_g": 28.4,
            			"sugar_g": 0.1
            		}
            	]
            }
        """.trimIndent()

        private fun generateClient(engine: HttpClientEngine) = HttpClient(engine) {
            install(ContentNegotiation) {
                json()
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }

    }

    @Test
    fun `given a valid response CalorieApi returns successful NetworkResult`() = runBlocking {

        val fakeEngine = MockEngine {
            respond(
                content = VALID_RESPONSE,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val fakeClient = generateClient(engine = fakeEngine)

        val actual = CalorieApi(client = fakeClient).getCalories(query = "rice")

        assertTrue(message = "response should be successful") {
            actual.isSuccessful
        }

    }

    @Test
    fun `given a valid response CalorieApi returns valid ItemsDto data`() = runBlocking {

        val fakeEngine = MockEngine {
            respond(
                content = VALID_RESPONSE,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val fakeClient = generateClient(engine = fakeEngine)

        val expected = Json.decodeFromString<ItemsDto>(VALID_RESPONSE)
        val actual = CalorieApi(client = fakeClient).getCalories(query = "rice")

        val data = actual.data

        assertEquals(message = "parses valid calorie items", expected = expected, actual = data)
    }

    @Test
    fun `given a invalid response CalorieApi returns unsuccessful NetworkResult`() = runBlocking {

        val fakeEngine = MockEngine {
            respond(
                content = INVALID_RESPONSE,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val fakeClient = generateClient(engine = fakeEngine)

        val actual = CalorieApi(client = fakeClient).getCalories(query = "rice")

        assertTrue(message = "response should not be successful") {
            actual.isSuccessful.not()
        }

    }

    @Test
    fun `given a invalid response CalorieApi returns null as NetworkResult data`() = runBlocking {

        val fakeEngine = MockEngine {
            respond(
                content = INVALID_RESPONSE,
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val fakeClient = generateClient(engine = fakeEngine)

        val actual = CalorieApi(client = fakeClient).getCalories(query = "rice")

        val data = actual.data

        assertEquals(
            message = "returns null when parsing response data",
            expected = null,
            actual = data
        )
    }

}