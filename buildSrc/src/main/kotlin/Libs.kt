object Libs {
    private const val kotlinVersion = "1.4.0"
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
    const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.1"
    const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
    const val material = "com.google.android.material:material:1.3.0-alpha02"
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"

    // Retrofit
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    const val retrofitRxjava = "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion"
    const val okLogging = "com.squareup.okhttp3:logging-interceptor:4.7.2"

    // Lifecycle
    private const val lifecycleVersion = "2.2.0"
    const val lifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    const val lifecycleSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycleVersion"

    // Room
    private const val roomVersion = "2.2.5"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"

    // Coroutine
    private const val coroutineVersion = "1.3.9"
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
    const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion"

    // Glide
    private const val glideVersion = "4.11.0"
    const val glideRuntime = "com.github.bumptech.glide:glide:$glideVersion"
    const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"

    // Timber
    const val timber = "com.jakewharton.timber:timber:4.7.1"

    // Koin
    const val koinVersion = "2.1.5"
    const val koinCore = "org.koin:koin-core:$koinVersion"
    const val koinAndroid = "org.koin:koin-android:$koinVersion"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
    const val koinScope = "org.koin:koin-androidx-scope:$koinVersion"

    // Navigation
    const val navigationVersion = "2.3.0"
    const val navigationRuntimeKtx = "androidx.navigation:navigation-runtime-ktx:$navigationVersion"
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

    // Paging
    const val paging = "androidx.paging:paging-runtime-ktx:3.0.0-alpha07"

    // Test
    const val junit = "junit:junit:4.13"
    const val mockitoCore = "org.mockito:mockito-core:3.3.3"
    const val archCore = "android.arch.core:core-testing:1.1.1"

    const val testRunner = "com.android.support.test:runner:1.0.2"
    const val espressoCore = "com.android.support.test.espresso:espresso-core:3.0.2"
    const val roomTest = "android.arch.persistence.room:testing:1.1.1"

    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:4.4.0"
    const val kotlinTest = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

    const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
}
