plugins {
    id("java")
    id("com.gradleup.shadow") version "9.4.1"
}

group = "dev.blavez"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven("https://repo.okaeri.cloud/releases")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")

    implementation("com.google.inject:guice:7.0.0")
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:6.1.0-beta.4")

    implementation("org.bstats:bstats-bukkit:3.2.1")
}

tasks.shadowJar {
    archiveFileName.set("ArpeggioFortuneBlocks-${project.version}.jar")

    relocate("com.google.inject", "dev.blavez.libs.guice")
    relocate("eu.okaeri.configs", "dev.blavez.libs.conigs")

    relocate("org.bstats", "dev.blavez.libs.bstats")
}