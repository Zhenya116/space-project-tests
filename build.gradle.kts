plugins {
    java
    id("io.qameta.allure") version "2.12.0"
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

allure {
    adapter {
        frameworks { junit5 {} }
        autoconfigure.set(true)
    }
}

subprojects {
    apply(plugin = "java")
    repositories { mavenCentral() }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.10.2"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testImplementation("io.qameta.allure:allure-junit5:2.24.0")
        testImplementation("org.hamcrest:hamcrest:2.2")
        testImplementation("org.aspectj:aspectjweaver:1.9.21")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        systemProperty("env", System.getProperty("env", "test"))
        testLogging {
            showStandardStreams = true
            events("passed", "failed", "skipped")
        }
    }
}
