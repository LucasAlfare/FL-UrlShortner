plugins {
  kotlin("jvm") version "2.1.20"
  id("org.jetbrains.kotlin.plugin.serialization") version "2.1.20"
}

group = "com.lucasalfare"
version = "1.0"

repositories {
  mavenCentral()
  maven { url = uri("https://jitpack.io")}
}

dependencies {
  implementation("com.github.LucasAlfare:FL-Base:1.1")
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(21)
}