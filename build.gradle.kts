buildscript {

}// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.1" apply false
    id("com.android.library") version "7.4.1" apply false
    kotlin("android") version "1.8.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    kotlin("plugin.serialization") version "1.8.0" apply false
    kotlin("kapt") version "1.8.0" apply false
}