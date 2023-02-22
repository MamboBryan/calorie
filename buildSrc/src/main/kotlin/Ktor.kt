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

    private const val VERSION = "2.1.0"

    val core by lazy { "io.ktor:ktor-client-core:$VERSION" }
    val serialization by lazy { "io.ktor:ktor-serialization-kotlinx-json:$VERSION" }
    val logging by lazy { "io.ktor:ktor-client-logging-jvm:$VERSION" }
    val contentNegotiation by lazy { "io.ktor:ktor-client-content-negotiation:$VERSION" }
    val okHttp by lazy { "io.ktor:ktor-client-okhttp:$VERSION" }

}

fun DependencyHandler.ktor() {
    implementation(Ktor.core)
    implementation(Ktor.okHttp)
    implementation(Ktor.logging)
    implementation(Ktor.serialization)
    implementation(Ktor.contentNegotiation)
}
