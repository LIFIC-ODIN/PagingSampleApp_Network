buildscript {
    extra.apply {
        set("kotlin_version", "1.3.72")
        set("hilt_version", "2.28-alpha")
    }
    repositories {
        google()
        jcenter()
    }
    dependencies {
        val kotlinVersion = project.extra["kotlin_version"] as String
        val hiltVersion = project.extra["hilt_version"] as String
        classpath("com.android.tools.build:gradle:4.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
