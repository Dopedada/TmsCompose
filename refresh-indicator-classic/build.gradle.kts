@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins{
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeCompiler)
}

kotlin{
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
        freeCompilerArgs.add("-opt-in=kotlinx.cinterop.ExperimentalForeignApi")
    }
    applyDefaultHierarchyTemplate()
    androidTarget{
        publishLibraryVariants("release")
    }
    iosArm64()
    iosSimulatorArm64()


    sourceSets{
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.compose.bom))

            compileOnly(projects.refresh)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.animation)
            implementation(compose.components.resources)
        }
    }
}

android{
    namespace="com.king.ultraswiperefresh.indicator.classic"
    compileSdk= libs.versions.android.compileSdk.get().toInt()
    defaultConfig{
        minSdk=libs.versions.android.minSdk.get().toInt()
    }
    lint{
        targetSdk=libs.versions.android.targetSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvmTarget.get())
    }
    publishing {
        singleVariant("release"){
            withJavadocJar()
            withSourcesJar()
        }
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "ultraswiperefresh.refresh_indicator_classic.generated.resources"
    generateResClass = always
}
