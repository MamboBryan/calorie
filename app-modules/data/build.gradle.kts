apply {
    from("$rootDir/base-module.gradle.kts")
}

dependencies {

    implementation(project(":app-modules:remote"))
    implementation(project(":app-modules:local"))

    room()
}