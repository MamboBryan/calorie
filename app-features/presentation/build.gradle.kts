apply {
    from("$rootDir/base-feature.gradle.kts")
}

dependencies {

    implementation(project(":app-modules:ui"))
    implementation(project(":app-features:calories"))
    implementation(project(":app-features:calorie"))

}