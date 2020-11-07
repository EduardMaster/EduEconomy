plugins {
    java
    kotlin("jvm") version "1.3.72"
    `maven-publish`
}

group = "net.eduard"
version = "1.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_1_8
repositories {
    mavenCentral()
    mavenLocal()
    maven("https://jitpack.io/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {

    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("net.eduard:eduardapi:1.0-SNAPSHOT")
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}
tasks.withType<Jar>{
    destinationDir = file("E:\\Tudo\\Minecraft - Server\\Servidor Teste\\plugins\\")


}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }


}
tasks.withType<JavaCompile>{
    options.encoding = "UTF-8"
}

publishing {
    publications {
        create<MavenPublication>("EduEconomy") {
            groupId = "net.eduard"
            artifactId = "economy"
            version = project.version as String

            from(components["java"])
        }
    }
}