import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.ProjectDependency


/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Tue 21 Feb 2023
 */
fun DependencyHandler.implementation(name: String) {
    add("implementation", name)
}

fun DependencyHandler.implementation(name: Any) {
    add("implementation", name)
}

fun DependencyHandler.kapt(name: String) {
    add("kapt", name)
}

fun DependencyHandler.compileOnly(name: String) {
    add("compileOnly", name)
}

fun DependencyHandler.api(name: String) {
    add("api", name)
}

fun DependencyHandler.testImplementation(name: String) {
    add("testImplementation", name)
}

fun DependencyHandler.androidTestImplementation(name: String) {
    add("androidTestImplementation", name)
}

fun DependencyHandler.androidTestImplementation(notation: Any) {
    add("androidTestImplementation", notation)
}

fun DependencyHandler.debugImplementation(notation: Any) {
    add("debugImplementation", notation)
}

fun DependencyHandler.project(
    path: String,
    configuration: String? = null
): ProjectDependency =

    uncheckedCast(
        project(
            if (configuration != null) mapOf("path" to path, "configuration" to configuration)
            else mapOf("path" to path)
        )
    )

@Suppress("unchecked_cast")
fun <T> uncheckedCast(obj: Any?): T =
    obj as T