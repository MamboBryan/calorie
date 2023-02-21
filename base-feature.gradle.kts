apply(from = "$rootDir/base-android-feature.gradle")

dependencies {

    common()

    testing()

    uiTesting()

    compose()

    accompanist()

    hilt()

}
//
//kapt {
//    correctErrorTypes = true
//}