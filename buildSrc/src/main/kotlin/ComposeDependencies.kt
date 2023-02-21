import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Tue 21 Feb 2023
 */
object Accompanist {

    private const val VERSION = "0.29.1-alpha"

    const val pager = "com.google.accompanist:accompanist-pager:$VERSION"
    const val systemUi = "com.google.accompanist:accompanist-systemuicontroller:$VERSION"
    const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$VERSION"
}

fun DependencyHandler.compose() {
    implementation(platform("androidx.compose:compose-bom:2023.01.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.01.00"))

    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.material3:material3-window-size-class")

    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
}

fun DependencyHandler.accompanist() {
    implementation(Accompanist.systemUi)
    implementation(Accompanist.pager)
    implementation(Accompanist.pagerIndicators)
}