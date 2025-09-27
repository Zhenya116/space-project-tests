plugins {
    java
    id("io.qameta.allure") version "2.12.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories { mavenCentral() }

dependencies {
    // JUnit 5 (Jupiter)
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // Rest-Assured
    testImplementation("io.rest-assured:rest-assured:5.5.0")
    testImplementation("io.rest-assured:json-schema-validator:5.5.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
    // Allure (JUnit5 + Rest-Assured)
    testImplementation("io.qameta.allure:allure-junit5:2.24.0")
    testImplementation("io.qameta.allure:allure-rest-assured:2.24.0")

    // твой общий модуль
    testImplementation(project(":modules:common"))
}

tasks.test {
    // Включаем JUnit 5
    useJUnitPlatform()
    // передаём env в тесты (по-умолчанию "test")
    systemProperty("env", System.getProperty("env", "test"))
}

allure {
    report { version.set("2.30.0") } // версию отчётника можно менять по желанию
    adapter {
        autoconfigure.set(true)
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set("2.24.0")
            }
        }
    }
}

sourceSets {
    test {
        java.setSrcDirs(listOf("src/test/java"))
        resources.setSrcDirs(listOf("src/test/resources"))
        resources.includes.add("**/*")
    }
}
