import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

plugins {
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.dokka") version "1.4.10"
    id("maven-publish")
    id("com.vanniktech.maven.publish") version "0.13.0"
}

group = "io.onema"
version = "0.1.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    // General
    implementation(kotlin("stdlib"))

    // Test
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")

    // Documentation
    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.4.10")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl.set(URL("https://github.com/Kotlin/kotlin-examples/tree/master/" +
                        "gradle/dokka/dokka-gradle-example/src/main/kotlin"
                ))
                remoteLineSuffix.set("#L")
            }
        }
    }
}

mavenPublish {
    // ...
    nexus {
        baseUrl = "https://oss.sonatype.org/service/local/" // defaults to "https://oss.sonatype.org/service/local/"
        stagingProfile = "io.onema" // defaults to the SONATYPE_STAGING_PROFILE Gradle property or the GROUP Gradle Property if not set
    }
}