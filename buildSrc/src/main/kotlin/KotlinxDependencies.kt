import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Tue 21 Feb 2023
 */
object Kotlinx {

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"

}

fun DependencyHandler.kotlinx(){
    implementation(Kotlinx.coroutines)
    implementation(Kotlinx.serialization)
}