apply {
    from("$rootDir/base-feature.gradle.kts")
}

dependencies {

    implementation(project(":app-modules:ui"))
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

}