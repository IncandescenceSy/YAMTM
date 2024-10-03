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
import static com.example.examplemod.ExampleMod.TOOL_TYPE_REGISTRY_KEY;

public record ToolData(List<Holder<ToolMaterial>> material, List<Holder<ToolType>> type) {

    public static final StreamCodec<RegistryFriendlyByteBuf, ToolData> STREAM_CODEC_DATA = StreamCodec.composite(
            ByteBufCodecs.holderRegistry(TOOL_MATERIAL_REGISTRY_KEY).apply(ByteBufCodecs.list()), ToolData::material,
            ByteBufCodecs.holderRegistry(TOOL_TYPE_REGISTRY_KEY).apply(ByteBufCodecs.list()), ToolData::type, ToolData::new
    );

    public static final Codec<ToolData> CODEC_DATA = RecordCodecBuilder.create(instance -> // Given an instance
            instance.group( // Define the fields within the instance
                    RegistryFixedCodec.create(TOOL_MATERIAL_REGISTRY_KEY).listOf().fieldOf("material").forGetter(ToolData::material),
                    RegistryFixedCodec.create(TOOL_TYPE_REGISTRY_KEY).listOf().fieldOf("type").forGetter(ToolData::type)
            ).apply(instance, ToolData::new) // Define how to create the object
    );
}
