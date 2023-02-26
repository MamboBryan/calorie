apply(from = "$rootDir/base-android-feature.gradle")

dependencies {

    implementation(project(":app-modules:data"))

    common()

    testing()

    uiTesting()

    compose()

    accompanist()

    hilt()

}
