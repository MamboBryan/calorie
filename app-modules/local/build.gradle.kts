apply {
    from("$rootDir/base-module.gradle.kts")
}

dependencies {
    room()
    uiTesting()
}