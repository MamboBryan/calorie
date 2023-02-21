import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * @project Calorie
 * @author mambobryan
 * @email mambobryan@gmail.com
 * Tue 21 Feb 2023
 */
object JetpackCompose {
    val ui by lazy { "androidx.compose.ui:ui:1.2.1" }
    val uiTooling by lazy { "androidx.compose.ui:ui-tooling:1.2.1" }
    val uiPreview by lazy { "androidx.compose.ui:ui-tooling-preview:1.2.1" }
    val foundation by lazy { "androidx.compose.foundation:foundation:1.2.1" }
    val material by lazy { "androidx.compose.material:material:1.2.1" }
    val activity by lazy { "androidx.activity:activity-compose:1.5.1" }
    val icons by lazy { "androidx.compose.material:material-icons-extended:1.2.1" }
}

object Accompanist {

    private const val VERSION = "0.29.1-alpha"

    val pager by lazy { "com.google.accompanist:accompanist-pager:$VERSION" }
    val systemUi by lazy { "com.google.accompanist:accompanist-systemuicontroller:$VERSION" }
    val pagerIndicators by lazy { "com.google.accompanist:accompanist-pager-indicators:$VERSION" }
}

fun DependencyHandler.jetpackCompose() {
    implementation(JetpackCompose.ui)
    implementation(JetpackCompose.uiTooling)
    implementation(JetpackCompose.uiPreview)
    implementation(JetpackCompose.foundation)
    implementation(JetpackCompose.material)
    implementation(JetpackCompose.activity)
    implementation(JetpackCompose.icons)
}

fun DependencyHandler.accompanist() {
    implementation(Accompanist.systemUi)
    implementation(Accompanist.pager)
    implementation(Accompanist.pagerIndicators)
}