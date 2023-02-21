apply(from = "$rootDir/base-android-module.gradle")

dependencies {

    common()

    testing()

    kotlinx()

    hilt()

}

kapt {
    correctErrorTypes = true
}