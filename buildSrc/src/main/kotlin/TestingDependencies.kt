import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Tue 21 Feb 2023
 */
fun DependencyHandler.testing() {
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}

fun DependencyHandler.uiTesting(){
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}