object Version {
    const val kotlin = "1.4.0"
    const val material = "1.3.0-alpha02"
    const val constraintLayout = "2.0.1"
    const val appCompat = "1.2.0"
    const val ktx = "1.0.1"

    const val retrofit = "2.9.0"
    const val okhttpLogging = "4.7.2"

    const val coroutines = "1.3.9"
    const val koin = "2.1.5"

    const val room = "2.2.5"

    const val glide = "4.11.0"

    const val navigation = "2.3.0"
    const val paging = "3.0.0-alpha07"
    const val recyclerView = "1.0.0"
    const val lifecycle = "2.2.0"

    const val timber = "4.7.1"

    const val jUnit = "4.13"
    const val testRunner = "1.1.0-alpha4"
    const val espresso = "3.1.1"
    const val mockito = "3.3.3"
    const val mockTestRunner = "0.3.1"
    const val rules = "1.1.0"
    const val ext = "1.0.0"
}

object Android {
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
    val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    val cardView = "androidx.cardview:cardview:${Version.appCompat}"
    val ktx = "androidx.core:core-ktx:${Version.ktx}"
    val design = "com.google.android.material:material:${Version.material}"

    //Log
    val timber = "com.jakewharton.timber:timber:${Version.timber}"
}

object Jetpack {
    val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
    val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
    val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    val lifecycleExt =  "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
    val navigationRuntimeKtx = "androidx.navigation:navigation-runtime-ktx:${Version.navigation}"
    val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"
    // Paging
    val paging = "androidx.paging:paging-runtime-ktx:${Version.paging}"
}

object Dependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
}

object Retrofit {
    val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    val gsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    val okHttpLogging = "com.squareup.okhttp3:logging-interceptor:${Version.okhttpLogging}"
    val mock = "com.squareup.retrofit2:retrofit-mock:${Version.retrofit}"
}

object Coroutines {
    val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
}

object Glide {
    val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    val compiler = "com.github.bumptech.glide:compiler:${Version.glide}"
}

object Koin {
    const val koinCore = "org.koin:koin-core:${Version.koin}"
    const val koinAndroid = "org.koin:koin-android:${Version.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Version.koin}"
    const val koinScope = "org.koin:koin-androidx-scope:${Version.koin}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Version.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
    const val roomKtx = "androidx.room:room-ktx:${Version.room}"
}

object Testing {
    val jUnit = "junit:junit:${Version.jUnit}"
    val testRunner = "androidx.test:runner:${Version.testRunner}"
    val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:${Version.espresso}"
    val espressoIdleResources = "androidx.test.espresso:espresso-idling-resource:${Version.espresso}"
    val mockito = "org.mockito:mockito-inline:${Version.mockito}"
    val mockKtRunner = "de.jodamob.kotlin:kotlin-runner-junit4:${Version.mockTestRunner}"
    val runner = "androidx.test:runner:${Version.rules}"
    val rules = "androidx.test:rules:${Version.rules}"
    val core = "androidx.test:core:${Version.rules}"

    val extJunit = "androidx.test.ext:junit:${Version.ext}"
    val extTruth = "androidx.test.ext:truth:${Version.ext}"
}