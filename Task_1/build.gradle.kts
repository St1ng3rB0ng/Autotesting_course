plugins {
    id("java")
}

group = "student.anatoliy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    testImplementation("org.testng:testng:7.10.2")
}

tasks.test {
    useTestNG()

    testLogging {
        events("passed", "skipped", "failed")
    }
}