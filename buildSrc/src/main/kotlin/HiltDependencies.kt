import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Tue 21 Feb 2023
 */
fun DependencyHandler.hilt(){
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
//    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
//    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha03")
}