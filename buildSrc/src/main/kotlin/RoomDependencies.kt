import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Thu 23 Feb 2023
 */
object Room {
    private const val VERSION = "2.5.0"
    val kotlinExtensions = "androidx.room:room-ktx:$VERSION"
    val runtime = "androidx.room:room-runtime:$VERSION"
    val compiler = "androidx.room:room-compiler:$VERSION"
    val testing = "androidx.room:room-testing:$VERSION"
}

fun DependencyHandler.room() {
    implementation(Room.kotlinExtensions)
    implementation(Room.runtime)
    testImplementation(Room.testing)
    kapt(Room.compiler)
}