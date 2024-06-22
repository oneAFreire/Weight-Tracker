plugins {
    alias(libs.plugins.jetbrainsKotlinJvm)
    id(libs.plugins.javaLibrary.get().pluginId)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.compileKotlin {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    // Testing
    testImplementation(libs.junit)
}