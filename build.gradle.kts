plugins {
    java
    id("io.qameta.allure") version "2.12.0"
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }
repositories { mavenCentral() }

dependencies {
    implementation(platform("io.qameta.allure:allure-bom:2.30.0"))

    // нужны в main:
    implementation("io.rest-assured:rest-assured:5.5.0")
    implementation("io.qameta.allure:allure-rest-assured")
    implementation("io.qameta.allure:allure-java-commons")

    // только для тестов:
    testImplementation("io.rest-assured:json-schema-validator:5.5.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("io.qameta.allure:allure-junit5")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("env", System.getProperty("env", "test"))
    systemProperty(
        "baseURI",
        System.getProperty("baseURI", project.findProperty("baseURI")?.toString() ?: "")
    )
}

allure {
    version.set("2.35.1")
    adapter {
        autoconfigure.set(true)
        aspectjWeaver.set(true)
        frameworks { junit5 { enabled.set(true) } }
    }
}
