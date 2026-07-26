Neoforge MDK 1.21.1 with Kotlin
========

A template for developing NeoForge mods using Kotlin for Forge and Gradle Kotlin DSL.

Migrating from Java, Groovy DSL to Kotlin is a huge pain. I decided to make this template so you can build mods right away instead of spending hours to set it up.

## Features

- Kotlin for Forge preconfigured
- Gradle Kotlin DSL (`build.gradle.kts`, `settings.gradle.kts`)
- Ready to use out of the box

This template targets Minecraft 1.21.1 only.
If you need another version, you can either migrate it yourself, follow this template, or use the Kotlin for Forge migration guide as a reference.

> [!NOTE]
> This template is generated from Official Neoforge MDK using NeoGradle, but later I changed it to ModDevGradle
>
> Also because it is not an official template, it potentially contains hidden bugs. Please report it if you find one. Contributions are always welcome.

## Original NeoForge MDK README:

Installation information
=======

This template repository can be directly cloned to get you started with a new
mod. Simply create a new repository cloned from this one, by following the
instructions provided by [GitHub](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template).

Once you have your clone, simply open the repository in the IDE of your choice. The usual recommendation for an IDE is either IntelliJ IDEA or Eclipse.

If at any point you are missing libraries in your IDE, or you've run into problems you can
run `gradlew --refresh-dependencies` to refresh the local cache. `gradlew clean` to reset everything 
{this does not affect your code} and then start the process again.

Mapping Names:
============
By default, the MDK is configured to use the official mapping names from Mojang for methods and fields 
in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this
license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

Additional Resources: 
==========
Community Documentation: https://docs.neoforged.net/  
NeoForged Discord: https://discord.neoforged.net/
