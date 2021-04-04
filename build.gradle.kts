plugins {
    java
    kotlin("jvm") version "1.4.21"

}

group = "net.eduard"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://jitpack.io/")
}


dependencies {
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("net.eduard:eduardapi:1.0-SNAPSHOT")
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly(kotlin("stdlib-jdk8"))

}
java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8


tasks {
    jar{
        destinationDirectory.set(file("E:\\Tudo\\Minecraft - Server\\Servidor Teste\\plugins\\"))
    }
    compileJava{
        options.encoding = "UTF-8"
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

