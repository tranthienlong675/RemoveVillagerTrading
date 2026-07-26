plugins {
    `java-library`
    `maven-publish`
    id("net.neoforged.moddev") version "2.0.142"
    id("idea")

    kotlin("jvm") version "2.4.10"
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.BIN
}

val mod_version: String by project
val mod_group_id: String by project
val mod_id: String by project
val neo_version: String by project
val minecraft_version: String by project
val minecraft_version_range: String by project
val loader_version_range: String by project
val mod_name: String by project
val mod_license: String by project
val parchment_mappings_version: String by project
val parchment_minecraft_version: String by project

version = mod_version
group = mod_group_id

sourceSets.main {
    resources {
        srcDir("src/generated/resources")

        exclude("**/*.bbmodel")
        exclude("src/generated/**/.cache")
    }
}

repositories {
    // Add KFF Maven repository
    maven {
        name = "Kotlin for Forge"
        setUrl("https://thedarkcolour.github.io/KotlinForForge/")
    }
}

base {
    archivesName.set(mod_id)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

neoForge {
    version = neo_version

    parchment {
        mappingsVersion.set(parchment_mappings_version)
        minecraftVersion.set(parchment_minecraft_version)
    }


    runs {
        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")

            logLevel = org.slf4j.event.Level.DEBUG
        }

        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }

        create("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }

        create("gameTestServer") {
            type = "gameTestServer"
            systemProperty("neoforge.enabledGameTestNamespaces", mod_id)
        }

        create("data") {
            data()

            programArguments.addAll(
                "--mod",
                mod_id,
                "--all",
                "--output",
                file("src/generated/resources").absolutePath,
                "--existing",
                file("src/main/resources").absolutePath
            )
        }
    }

    mods {
        create(mod_id) {
            sourceSet(sourceSets.main.get())
        }
    }
}

val localRuntime by configurations.creating

configurations.named("runtimeClasspath") {
    extendsFrom(localRuntime)
}

dependencies {
    implementation("net.neoforged:neoforge:${neo_version}")

    val kff_version: String by project
    implementation("thedarkcolour:kotlinforforge-neoforge:$kff_version")
}

val kff_version_range: String by project

tasks.withType<ProcessResources>().configureEach {
    var replaceProperties = mapOf(
        "minecraft_version"       to minecraft_version,
        "minecraft_version_range" to minecraft_version_range,
        "neo_version"             to neo_version,
        "loader_version_range"    to loader_version_range,
        "mod_id"                  to mod_id,
        "mod_name"                to mod_name,
        "mod_license"             to mod_license,
        "mod_version"             to mod_version,
        "kff_version_range"       to kff_version_range
    )

    inputs.properties(replaceProperties)
    expand(replaceProperties)
    from("src/main/templates")
    into("build/generated/sources/modMetadata")
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri(layout.projectDirectory.dir("repo"))
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
