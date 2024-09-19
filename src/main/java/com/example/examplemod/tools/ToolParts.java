package com.example.examplemod.tools;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;

import java.util.List;

import static com.example.examplemod.ExampleMod.TOOL_MATERIAL_REGISTRY_KEY;

public record ToolParts(List<Holder<ToolMaterial>> material) {

    public static final StreamCodec<RegistryFriendlyByteBuf, ToolParts> STREAM_CODEC = StreamCodec.composite(
  ByteBufCodecs.holderRegistry(TOOL_MATERIAL_REGISTRY_KEY).apply(ByteBufCodecs.list()), ToolParts::material, ToolParts::new
    );

    public static final Codec<ToolParts> CODEC = RecordCodecBuilder.create(instance -> // Given an instance
            instance.group( // Define the fields within the instance
                    RegistryFixedCodec.create(TOOL_MATERIAL_REGISTRY_KEY).listOf().fieldOf("material").forGetter(ToolParts::material)
            ).apply(instance, ToolParts::new) // Define how to create the object
    );
}
