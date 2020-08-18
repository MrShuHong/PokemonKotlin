package com.example.plugin

object Versions {

    val appcompat = "1.1.0"
    val core_ktx = "1.3.1"
    val constraintlayout = "2.0.0-rc1"
    val swiperefreshlayout = "1.1.0"
    val recyclerview = "1.1.0"
    val startup_runtime = "1.0.0-alpha02"
    val junit = "4.13"
    val androidTestJunit = "1.1.1"
    val espressoCore = "3.2.0"
    val material = "1.2.0"

}

object Depend {
    val junit = "junit:junit:${Versions.junit}"
    val androidTestJunit = "androidx.test.ext:junit:${Versions.androidTestJunit}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object AndroidX {

    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val swiperefreshlayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val startup_runtime = "androidx.startup:startup-runtime:${Versions.startup_runtime}"

}

object Android {
    val material = "com.google.android.material:material:${Versions.material}"
}

