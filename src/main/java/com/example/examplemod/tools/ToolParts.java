package com.example.examplemod.tools;

import com.example.examplemod.ExampleMod;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.RegistryFixedCodec;

import java.util.List;

public record ToolParts(List<ToolMaterial> material) {
    public static final Codec<ToolParts> TOOL_PARTS = RecordCodecBuilder.create(instance -> // Given an instance
            instance.group( // Define the fields within the instance
                    RegistryFixedCodec.create(ExampleMod.TOOL_MATERIAL_REGISTRY_KEY).listOf().fieldOf("material").forGetter(ToolParts::material)
            ).apply(instance, ToolParts::new) // Define how to create the object
    );
}
