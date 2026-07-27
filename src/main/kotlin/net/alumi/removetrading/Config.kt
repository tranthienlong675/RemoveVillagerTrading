package net.alumi.removetrading

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.neoforged.neoforge.common.ModConfigSpec
import java.util.function.Predicate
import java.util.function.Supplier

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
object Config {
    private val BUILDER = ModConfigSpec.Builder()
    @JvmField
    val SPEC: ModConfigSpec = BUILDER.build()
}
