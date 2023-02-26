import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Wed 22 Feb 2023
 */
/**
 * KTOR
 */
object Ktor {

    private const val VERSION = "2.2.3"

    val core = "io.ktor:ktor-client-core:$VERSION"
    val serialization = "io.ktor:ktor-serialization-kotlinx-json:$VERSION"
    val logging = "io.ktor:ktor-client-logging:$VERSION"
    val contentNegotiation = "io.ktor:ktor-client-content-negotiation:$VERSION"
    val okHttp = "io.ktor:ktor-client-okhttp:$VERSION"
    val mockEngine = "io.ktor:ktor-client-mock:$VERSION"

}

fun DependencyHandler.ktor() {
    implementation(Ktor.core)
    implementation(Ktor.okHttp)
    implementation(Ktor.logging)
    implementation(Ktor.mockEngine)
    implementation(Ktor.serialization)
    implementation(Ktor.contentNegotiation)
}
