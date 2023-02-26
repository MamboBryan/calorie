apply {
    from("$rootDir/base-feature.gradle.kts")
}

dependencies {

    implementation(project(":app-modules:ui"))

}